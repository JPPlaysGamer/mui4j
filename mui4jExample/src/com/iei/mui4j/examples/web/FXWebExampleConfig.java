package com.iei.mui4j.examples.web;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import com.iei.mui4j.MUIFile;
import com.iei.mui4j.Updater;
import com.iei.mui4j.components.ComponentWrapper;
import com.iei.mui4j.web.MUIWebConfiguration;

import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class FXWebExampleConfig extends MUIWebConfiguration {


	@Override
	public boolean useCurrentLocale() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Locale getInitialLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Updater updater() {
		// TODO Auto-generated method stub
		return new Updater() {
			
			@Override
			public void updateTranslation(MUIFile file, List<ComponentWrapper> table) {
				// TODO Auto-generated method stub
				onFXThread(() -> {
					
					select(table, "mnLang").asFx(Menu.class).setText(get(file, "mnLangs"));
					select(table, "miptbr").asFx(MenuItem.class).setText(get(file, "mnLangsEntries.mntmptbr"));
					select(table, "mienus").asFx(MenuItem.class).setText(get(file, "mnLangsEntries.mntmenus"));
					select(table, "mieses").asFx(MenuItem.class).setText(get(file, "mnLangsEntries.mntmeses"));
					select(table, "miClose").asFx(MenuItem.class).setText(get(file, "mntmExit"));
					select(table, "miAbout").asFx(MenuItem.class).setText(get(file, "mntmAbout"));
					select(table, "mnHelp").asFx(MenuItem.class).setText(get(file, "mnHelp"));
					select(table, "mnwin").asFx(MenuItem.class).setText(get(file, "mnWin"));
					
					Alert alert = select(table, "alert").asFxET(Alert.class);
					alert.setTitle(get(file, "alert.title"));
					alert.setHeaderText(get(file, "alert.header"));
					alert.setContentText(get(file, "alert.text"));
					
				});
			}
		};
	}

	@Override
	public URL getBaseURL() throws MalformedURLException {
		// TODO Auto-generated method stub
		return new URL("https://raw.githubusercontent.com/JPPlaysGamer/mui4j/main/mui4jExample/src/com/iei/mui4j/examples/fxlocales");
	}

}
