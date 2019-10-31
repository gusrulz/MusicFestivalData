package main;

import java.io.IOException;

public class Application {
	public static MusicFestival[] _musicFestivals;
	
	public static void main(String[] arguments) {
		String json = ConnectionHelper.getResponse("http://eacodingtest.digital.energyaustralia.com.au/api/v1/festivals");
		
		if (json == null) {
			System.out.println("Failed to download data. Exiting...");
			return;
		}
		
		JsonHelper.parseJson(json);
		
		ViewHelper.displayData();
		
		try {
			System.out.print("Press any key to exit...");
			System.in.read();
		} catch (IOException e) {
			System.out.println("An Unexpected error occurred. Exiting...");
			return;
		}
	}
}