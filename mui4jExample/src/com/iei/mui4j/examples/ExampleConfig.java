package com.iei.mui4j.examples;

import java.util.List;
import java.util.Locale;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.iei.mui4j.MUIConfiguration;
import com.iei.mui4j.MUIFile;
import com.iei.mui4j.Updater;
import com.iei.mui4j.components.ComponentWrapper;

public class ExampleConfig implements MUIConfiguration {

	@Override
	public boolean useDefaultPath() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getClassPath() {
		// TODO Auto-generated method stub
		return "/com/iei/mui4j/examples/swinglocales";
	}

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
				select(table, "mnLangs").asSwing(JMenu.class).setText(get(file, "mnLangs"));
				select(table, "mntmptbr").asSwing(JMenuItem.class).setText(get(file, "mnLangsEntries.mntmptbr"));
				select(table, "mntmenus").asSwing(JMenuItem.class).setText(get(file, "mnLangsEntries.mntmenus"));
				select(table, "mntmeses").asSwing(JMenuItem.class).setText(get(file, "mnLangsEntries.mntmeses"));
				select(table, "mntmExit").asSwing(JMenuItem.class).setText(get(file, "mntmExit"));
					
			}
		};
	}

}
