package main;

import com.google.gson.*;

public class JsonHelper {
	private static Gson g = new Gson();
	
	public static void parseJson(String json) {
		Application._musicFestivals = parseMusicFestivals(json);
	}
	
	public static MusicFestival[] parseMusicFestivals(String json) {
		return g.fromJson(json, MusicFestival[].class);
	}
}