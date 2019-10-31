package main;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.*;;

public class ConnectionHelper {
	public static String getResponse(String endpoint) {
		try {
			URL url = new URL(endpoint);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				System.out.println("Bad response - " + conn.getResponseMessage());
				return null;
			}
			
			return IOUtils.toString(conn.getInputStream(), StandardCharsets.UTF_8);
		}
		catch (IOException e) {
			return null;
		}
	}
}
