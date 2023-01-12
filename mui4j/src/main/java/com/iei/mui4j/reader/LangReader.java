package com.iei.mui4j.reader;

import java.io.InputStream;
import java.io.StringReader;
import java.nio.file.NoSuchFileException;
import java.util.Locale;

import org.apache.commons.lang3.LocaleUtils;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import com.iei.mui4j.MUIConfiguration;
import com.iei.mui4j.MUIFile;
import com.iei.mui4j.internal.IOUtils;

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
		String cpDir = getClassPathDir();
		
		String file = String.format("%s/%s_%s.xml", cpDir, loc.getLanguage(), loc.getCountry());
		InputStream inputFile = this.getClass().getResourceAsStream(file);
		if(inputFile == null)
			throw new NoSuchFileException(String.format("'%s' has not found for load", file));
		
		String xmlContent = IOUtils.readToEnd(inputFile);
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
	
	private String getClassPathDir() throws Exception {
		if(config.useDefaultPath())
			return "/locales";
		else if(config.getClassPath() != null)
			return config.getClassPath();
		else
			throw new Exception("No classpath specified.");
	}

	private Locale getLocale() throws Exception {
		if(force && locale != null)
			return locale;
		else if(config.useCurrentLocale())
			return Locale.getDefault();
		else if(config.getInitialLocale() != null)
			return config.getInitialLocale();
		else throw new Exception("No language selected!");
	}
}
