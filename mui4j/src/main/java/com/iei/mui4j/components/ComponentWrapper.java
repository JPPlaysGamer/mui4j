package com.iei.mui4j.components;

import java.util.Objects;

import javax.swing.JComponent;

import javafx.scene.Node;

/**
 * 
 * DOCUMENT ME
 *
 */
public class ComponentWrapper {
	
	/**
	 * 
	 * DOCUMENT ME
	 *
	 */
	public enum ComponentType {
		/**
		 * 
		 * DOCUMENT ME
		 *
		 */
		SWING, 
		
		/**
		 * 
		 * DOCUMENT ME
		 *
		 */
		JAVAFX
	}
	private String id;
	private Object component;
	private ComponentType type;
	
	public ComponentWrapper(String id, ComponentType type, Object component) {
		if(!id.matches("[a-zA-Z0-9]+")) {
			throw new IllegalArgumentException("Invalid id, letters and numbers only!");
		}
		
		this.id = id;
		this.type = type;
		this.component = component;
		
		Objects.requireNonNull(component);
		
		checkObject();
	}

	/**
	 * 
	 * DOCUMENT ME
	 *
	 */
	public <T extends Node> T asFx(Class<T> objectType) {
		return type == ComponentType.JAVAFX ? objectType.cast(component) : null;
	}
	
	/**
	 * 
	 * DOCUMENT ME
	 *
	 */
	public <T extends JComponent> T asSwing(Class<T> objectType) {
		return type == ComponentType.SWING ? objectType.cast(component) : null;
	}
	
	/**
	 * 
	 * DOCUMENT ME
	 *
	 */
	public ComponentType getType() {
		return this.type;
	}
	
	/**
	 * 
	 * DOCUMENT ME
	 *
	 */
	public String id() {
		return this.id;
	}
	
	private void checkObject() {
		switch(type) {
			case SWING:
				if(!(component instanceof JComponent))
					throw new ClassCastException("Invalid component! " + String.format("[%s != %s]", JComponent.class, component.getClass()));
				break;
			case JAVAFX:
				if(!(component instanceof Node))
					throw new ClassCastException("Invalid component! " + String.format("[%s != %s]", Node.class, component.getClass()));
				break;
		}
		
	}
}
