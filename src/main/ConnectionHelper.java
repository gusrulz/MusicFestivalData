package main;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.*;;

public class ConnectionHelper {
	public static String getResponse(String endpoint) {
		try {
			int maxRetryCount = 10;
			int count = 1;
			do {
				URL url = new URL(endpoint);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
				
				if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
					System.out.println("Bad response - " + conn.getResponseMessage());
					System.out.println("Attempt number " + count + " out of a maximum " + maxRetryCount + " attempts.");
				} else {
					return IOUtils.toString(conn.getInputStream(), StandardCharsets.UTF_8);
				}
			} while (count++ < 10);
			
			System.out.println("No successful response from API and max retry count has been reached.");
			return null;
		}
		catch (IOException e) {
			System.out.println("IOException thrown.");
			return null;
		}
	}
}
