package com.iei.mui4j;

import java.util.Locale;
import java.util.Objects;

import com.iei.mui4j.Updater.WrapperGetter;
import com.iei.mui4j.reader.LangReader;

/**
 * 
 * DOCUMENT ME
 *
 */
public final class MUIApp {
	private static MUIApp currentApp;
	
	private String name;
	private MUIConfiguration configuration;
	private LangReader reader;
	private MUIFile currentMUIFile;
	private WrapperGetter getter;
	
	private MUIApp(String appName) {
		this.name = appName;
	}
	
	/**
	 * 
	 * DOCUMENT ME
	 *
	 */
	public static void registerApp(String appName, Class<? extends MUIConfiguration> configClass) throws Exception {
		if(getInstance() != null)
			throw new IllegalStateException("MUIApp already defined.");
		
		Objects.requireNonNull(appName);
		if(appName.isBlank())
			throw new IllegalArgumentException("appName is empty or contains white spaces only.");
		
		currentApp = new MUIApp(appName);
		currentApp.configuration = configClass.getDeclaredConstructor().newInstance();
		
		Objects.requireNonNull(currentApp.configuration.updater(), "Updater required.");
		
		currentApp.reader = new LangReader(currentApp.configuration);
		currentApp.currentMUIFile = currentApp.reader.read();
		
		
	}
	
	/**
	 * 
	 * DOCUMENT ME
	 *
	 */
	public static void registerAndUpdate(String appName, Class<? extends MUIConfiguration> configClass, WrapperGetter getter) throws Exception {
		registerApp(appName, configClass);
		
		getInstance().linkComponents(getter);
		
		currentApp.configuration
			.updater()
			.updateTranslation(currentApp.currentMUIFile, getInstance().getter.getWrappers());
	}
	
	/**
	 * 
	 * DOCUMENT ME
	 *
	 */
	public static MUIApp getInstance() {
		return currentApp;
	}
	
	/**
	 * 
	 * DOCUMENT ME
	 *
	 */
	public void linkComponents(WrapperGetter getter) {
		Objects.requireNonNull(getter);
		this.getter = getter;
	}
	
	/**
	 * 
	 * DOCUMENT ME
	 *
	 */
	public void setNewLang(Locale loc) throws Exception {
		this.reader.switchLang(loc);
		this.currentMUIFile = this.reader.read();
		this.configuration
			.updater()
			.updateTranslation(currentMUIFile, this.getter.getWrappers());
	}
	
	/**
	 * 
	 * DOCUMENT ME
	 *
	 */
	public String getName() {
		return name;	}
}
