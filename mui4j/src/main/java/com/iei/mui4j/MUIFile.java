package com.iei.mui4j;

import java.util.Locale;
import java.util.Objects;

import org.apache.commons.lang3.LocaleUtils;
import org.dom4j.Document;
import org.dom4j.Element;

public class MUIFile {
	private Document xmlDoc;
	private Locale locale;
	
	public MUIFile(Document doc, Locale locale) {
		this.xmlDoc = doc;
		this.locale = locale;
	}
	
	/**
	 * 
	 * DOCUMENT ME
	 *
	 */
	public void validate() throws Exception {
		Element root = getRoot();
		if(root == null)
			throw new Exception("Root not found. (You forgot add 'com.iei.mui4j' element?)");
		
		String type = Objects.requireNonNull(root.attributeValue("translation")
				, "'translation' not found in 'com.iei.mui4j' element. (You forgot add 'translation' attribute?)");
		if(type.isBlank()) {
			throw new Exception("'translation' not found in 'com.iei.mui4j' element. (You forgot add 'translation' attribute?)");
		}
		
		testLocale(type);
		
		String loc = String.format("%s_%s", this.locale.getLanguage(), this.locale.getCountry());
		
		if(!type.equalsIgnoreCase(loc))
			throw new Exception(String.format("'%s' was expected, but this file is '%s'", loc, type));
		
	}
	
	/**
	 * 
	 * DOCUMENT ME
	 *
	 */
	public Section from(String name) {
		Element section = (Element) getRoot()
				.selectSingleNode(name);
		
		if(section == null)
			return null;
		
		return new Section(section);
	}
	
	/**
	 * 
	 * DOCUMENT ME
	 *
	 */
	public String get(String name) {
		return ((Element)getRoot().selectSingleNode(name)).getText();
	}
	
	private Element getRoot() {
		return (Element)xmlDoc.selectSingleNode("com.iei.mui4j");
	}
	
	private void testLocale(String loc) {
		Locale newLocale = LocaleUtils.toLocale(loc);
		if(!LocaleUtils.isAvailableLocale(newLocale))
			throw new IllegalArgumentException(String.format("'%s' not avaliable as Locale", newLocale));
	}
}
