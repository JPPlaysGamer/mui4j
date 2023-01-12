package com.iei.mui4j.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class IOUtils {

	public static String readToEnd(InputStream stream) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String tmp = "";
		
		while((tmp = reader.readLine()) != null) {
			sb.append(tmp);
		}
		
		tmp = sb.toString();
		reader.close();
		return tmp;
	}
}
