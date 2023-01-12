package com.iei.mui4j;

import org.dom4j.Element;

public class Section {
	private Element section;

	public Section(Element section) {
		this.section = section;
	}
	
	/**
	 * 
	 * DOCUMENT ME
	 *
	 */
	public Section from(String name) {
		return new Section((Element) this.section.selectSingleNode(name));
	}
	
	/**
	 * 
	 * DOCUMENT ME
	 *
	 */
	public String get(String name) {
		return ((Element)this.section.selectSingleNode(name)).getText();
	}
}
