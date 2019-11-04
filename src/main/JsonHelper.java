package main;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.*;

public class JsonHelper {
	private static Gson g = new Gson();
	
	// Parses a json string and returns an array of RecordLabels
	public static RecordLabel[] parseJson(String json) {
		MusicFestival[] musicFestivals = parseMusicFestivals(json);
		return parseRecordLabels(musicFestivals);
		
	}
	
	// Uses Gson library to parse the json to an array of MusicFestivals
	public static MusicFestival[] parseMusicFestivals(String json) {
		return g.fromJson(json, MusicFestival[].class);
	}
	
	//  Creates an Array of RecordLabels to display data in desired format
	public static RecordLabel[] parseRecordLabels(MusicFestival[] festivals) {
		// HashMap using RecordLabel name from the Band object to lookup RecordLabel objects already created 
		HashMap<String, RecordLabel> recordLabels = new HashMap<String, RecordLabel>();
		
		// Iterate over each festival and band to find any new RecordLabels
		for (MusicFestival festival : festivals) {
			if (festival.getBands() != null) {
				for (Band band : festival.getBands()) {
					if (band.getFestivals() == null) {
						band.setFestivals(new ArrayList<MusicFestival>());
					}
					band.getFestivals().add(festival);
					String rlName = band.getRecordLabel();
					if (rlName != null && !rlName.equals("")) {
						// If the RecordLabel doesn't exist in the HashMap, create it
						if (!recordLabels.containsKey(rlName)) {
							RecordLabel rl = new RecordLabel();
							rl.setBands(new ArrayList<Band>());
							rl.getBands().add(band);
							rl.setName(rlName);
							recordLabels.put(rlName, rl);
						} else {
							RecordLabel rl = recordLabels.get(rlName);
							rl.getBands().add(band);
						}
					} 
				}
			}
		}
		
		// Return HashMap values as an array of RecordLabels
		return recordLabels.values().toArray(new RecordLabel[0]);
	}
}