package com.iei.mui4j.reader;

import java.io.InputStream;
import java.io.StringReader;
import java.net.URI;
import java.nio.file.NoSuchFileException;
import java.util.Locale;

import org.apache.commons.lang3.LocaleUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import com.iei.mui4j.MUIConfiguration;
import com.iei.mui4j.MUIFile;
import com.iei.mui4j.internal.IOUtils;
import com.iei.mui4j.web.MUIWebConfiguration;

/**
 * 
 * DOCUMENT ME
 *
 */
public final class LangReader {
	private MUIConfiguration config;
	private boolean force;
	private Locale locale;
	
	public LangReader(MUIConfiguration muiconfig) {
		this.config = muiconfig;
	}
	
	/**
	 * 
	 * DOCUMENT ME
	 *
	 */
	public void switchLang(Locale newLocale) throws Exception {
		if(!LocaleUtils.isAvailableLocale(newLocale))
			throw new Exception("Invalid Locale: " + newLocale.toString());
		locale = newLocale;
		if(!force)
			force = true;
	}
	
	/**
	 * 
	 * DOCUMENT ME
	 *
	 */
	public MUIFile read() throws Exception {
		
		Locale loc = getLocale();
		String cpDir, xmlContent = null;
		
		if(this.config.getClass().getSuperclass().equals(MUIWebConfiguration.class)) {
			xmlContent = readURL(loc);
		} else {
			cpDir = getClassPathDir();
		
			String file = String.format("%s/%s_%s.xml", cpDir, loc.getLanguage(), loc.getCountry());
			InputStream inputFile = this.getClass().getResourceAsStream(file);
			if(inputFile == null)
				throw new NoSuchFileException(String.format("'%s' has not found for load", file));
		
			xmlContent = IOUtils.readToEnd(inputFile);
		}
		
		
		int startIndex = xmlContent.indexOf(60);
		xmlContent = xmlContent.substring(startIndex)
				.replaceAll(Character.toString(0), "")
				.trim();
		SAXReader saxr = new SAXReader();
		Document result = saxr.read(new InputSource(new StringReader(xmlContent)));
		MUIFile newFile = new MUIFile(result, loc);
		newFile.validate();
		
		return newFile;
	}
	
	private String readURL(Locale loc) throws Exception {
		
		MUIWebConfiguration web = (MUIWebConfiguration) this.config;
		URI toRequest = web.search().getURL(web.getBaseURL().toString(), loc).toURI();
		HttpGet translation = new HttpGet(toRequest);
		String resultString = null;
		
		try(CloseableHttpClient client = HttpClients.createDefault()) {
			CloseableHttpResponse response = client.execute(translation);
			resultString = IOUtils.readToEnd(response.getEntity().getContent());
		} catch (Exception e) {
			throw new Exception("Something went wrong when making http requests to acquire translations.", e);
		}
		
		return resultString;
	}

	private String getClassPathDir() throws Exception {
		if(config.useDefaultPath())
			return "/locales";
		else if(config.getClassPath() != null)
			return config.getClassPath();
		else
			throw new Exception("None classpath specified.");
	}

	private Locale getLocale() throws Exception {
		if(force && locale != null)
			return locale;
		else if(config.useCurrentLocale())
			return Locale.getDefault();
		else if(config.getInitialLocale() != null)
			return config.getInitialLocale();
		else throw new Exception("None language selected!");
	}
}
