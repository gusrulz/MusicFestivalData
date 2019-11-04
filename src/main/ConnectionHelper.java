package main;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.*;;

public class ConnectionHelper {
	public static String getResponse(String endpoint) {
		try {
			final int MAX_RETRY_COUNT = 10;
			int count = 1;
			do { 
				// Attempt to connect to the API up to the maxRetryCount as the API sometimes rejects connections if there are too many request
				URL url = new URL(endpoint);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
				
				// Any non-200 response code can be considered a failure as the API documentation specifies 200 and 429 as the only responses
				if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
					System.out.println("Bad response - " + conn.getResponseMessage());
					System.out.println("Attempt number " + count + " out of a maximum " + MAX_RETRY_COUNT + " attempts.");
					System.out.println("Retrying...");
				} else {
					// Return the response stream as a string
					return IOUtils.toString(conn.getInputStream(), StandardCharsets.UTF_8);
				}
			} while (count++ < MAX_RETRY_COUNT);
			
			System.out.println("No successful response from API and max retry count has been reached.");
			return null;
		}
		// Catching IOException in case response stream cannot be converted to a string
		catch (IOException e) {
			System.out.println("IOException thrown. Cannot convert Input Stream to string.");
			return null;
		}
	}
}