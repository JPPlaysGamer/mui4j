package com.iei.mui4j;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Locale;

import org.junit.jupiter.api.Test;

import static com.iei.mui4j.TestUtil.*;
import com.iei.mui4j.reader.LangReader;

class Example {

	@Test
	void test() throws Exception {
		
		LangReader lr = new LangReader(new ConfigExample());
		MUIFile file = lr.read();
		
		sysout(file.get("test") + "\n--------------------------\n");
		sysout(file.from("myOtherSection").get("text1") + file.from("myOtherSection").get("text2"));
		sysout(String.format("%s\n\n", file.get("there")));
		sysout(String.format("-> %s\n", file.from("mySection").get("fruit1")));
		sysout(String.format("-> %s\n", file.from("mySection").get("fruit2")));
		sysout(String.format("-> %s\n", file.from("mySection").get("fruit3")));
		
		assertTrue(true);
	}
	
	class ConfigExample implements MUIConfiguration {

		@Override
		public boolean useDefaultPath() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public String getClassPath() {
			// TODO Auto-generated method stub
			return "/customLocalesFolder";
		}

		@Override
		public boolean useCurrentLocale() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Locale getInitialLocale() {
			// TODO Auto-generated method stub
			return new Locale("en", "US");
		}

		@Override
		public Updater updater() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

}
