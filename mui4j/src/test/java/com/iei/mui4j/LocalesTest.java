package com.iei.mui4j;

import static com.iei.mui4j.TestUtil.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

class LocalesTest {

	@Test
	void test() {
		sysout("Java Locales List\n");
		sysout("--------------------------------\n\n");
		
		List<Locale> locales = new ArrayList<>(
			new HashSet<>(
					Arrays.asList(Locale.getAvailableLocales())
					.stream()
					.map(t -> Pair.with(t.getLanguage(), t.getCountry()))
					.distinct()
					.map(p -> new Locale(p.getValue0(), p.getValue1()))
					.toList()
			)
		);
		
		for(Locale l : locales) {
			sysout(String.format("\n-> %s (%s)\n", l.getDisplayLanguage(), l.getDisplayCountry()));
		}
		
		sysout("\n\n");
		
		assertTrue(true);
	}

}
