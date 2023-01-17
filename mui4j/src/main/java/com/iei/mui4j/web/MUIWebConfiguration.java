package com.iei.mui4j.web;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Locale;

import com.iei.mui4j.MUIConfiguration;

/**
 * 
 * Provides a web based configuration for an Application what use <code>mui4j</code>.
 * 
 * @author JPPlaysGamer
 *
 */
public abstract class MUIWebConfiguration implements MUIConfiguration {

	@Override
	public boolean useDefaultPath() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getClassPath() {
		throw new UnsupportedOperationException();
	}
	
	public abstract URL getBaseURL() throws MalformedURLException;
	public WebSupplier search() {
		return WebSupplier.DEFAULT;
	}
	
	public interface WebSupplier {
		public String getURL0(String base, Locale locale);
		public default URL getURL(String base, Locale locale) throws MalformedURLException, URISyntaxException {
			URL url = new URL(getURL0(base, locale)).toURI().toURL();
			
			return url;
		}
		
		public WebSupplier DEFAULT = new WebSupplier() {
			
			@Override
			public String getURL0(String base, Locale locale) {
				// TODO Auto-generated method stub
				return String.format("%s/%s_%s.xml", base, locale.getLanguage(), locale.getCountry());
			}
		};
	}
}
