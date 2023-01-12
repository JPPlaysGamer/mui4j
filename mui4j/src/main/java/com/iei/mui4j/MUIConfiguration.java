package com.iei.mui4j;

import java.util.Locale;

/**
 * 
 * Provides a configuration for an Application what use <code>mui4j</code>.
 * @author JPPlaysGamer
 *
 */
public interface MUIConfiguration {
	/**
	 * Determines if the <code>mui4j</code> use as default the <code>"/locales"</code> (in <code>ClassPath</code>) for all the translations.
	 * @return <code>true</code> for use <code>"/locales"</code>, else, <code>false</code>.
	 */
	public boolean useDefaultPath();
	/**
	 * If not want use default path, set other path to the translations.
	 * @return A {@link String} with a valid <code>ClassPath</code>.
	 */
	public String getClassPath();
	/**
	 * Determines if the <code>mui4j</code> load translations for the current language of system.
	 * @return <code>true</code> to use system language, else, <code>false</code>.
	 */
	public boolean useCurrentLocale();
	/**
	 * Gets the initial language to be loaded if not want use the current system language.
	 * @return A valid {@link Locale} object with country and language code.
	 */
	public Locale getInitialLocale();
	/**
	 * Gets a {@link Updater} object. When a change is applied the {@link Updater} will execute
	 * actions like update translations.
	 * @return A {@link Updater} object, cannot be <code>null</code>.
	 */
	public Updater updater();
	
}
