package com.iei.mui4j;

import java.util.List;
import java.util.Objects;

import com.iei.mui4j.components.ComponentWrapper;

/**
 * 
 * DOCUMENT ME
 *
 */
public interface Updater {
	
	/**
	 * 
	 * DOCUMENT ME
	 *
	 */
	public void updateTranslation(MUIFile file, List<ComponentWrapper> table);
	
	/**
	 * 
	 * DOCUMENT ME
	 *
	 */
	public default ComponentWrapper select(List<ComponentWrapper> table, String id) {
		return table.stream()
				.filter(cw -> cw.id().equalsIgnoreCase(id))
				.findFirst()
				.get();
	}
	
	/**
	 * 
	 * DOCUMENT ME
	 *
	 */
	public default String get(MUIFile file, String translationName) {
		Objects.requireNonNull(translationName);
		int size = translationName.length();
		if(!translationName.matches("[a-zA-Z0-9.]+") || 
				!Character.isLetter(translationName.toCharArray()[0]) || 
				!Character.isLetterOrDigit(translationName.toCharArray()[size-1]) ||
				translationName.isBlank())
			throw new IllegalArgumentException("Invalid translationName, The string must be formed without symbols at "
					+ "the beginning and end and only composed of letters and numbers and for sections separated by '.'");
		
		String[] parts = translationName.split("\\.");
		String result = "";
		if(parts.length == 1)
			result = file.get(parts[0]);
		else if(parts.length > 1) {
			String lastElement = parts[parts.length - 1];
			Section cursor = null ;
			for(String n : parts) {
				if(!n.equalsIgnoreCase(lastElement)) {
					cursor = Objects.isNull(cursor) ? file.from(n) : cursor.from(n);
				} else break;
			}
			
			result = cursor.get(lastElement);
		}
		
		return result;
	}
	
	/**
	 * 
	 * DOCUMENT ME
	 *
	 */
	public interface WrapperGetter{
		
		/**
		 * 
		 * DOCUMENT ME
		 *
		 */
		public List<ComponentWrapper> getWrappers();
	}
}
