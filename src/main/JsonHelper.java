package main;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.*;

public class JsonHelper {
	private static Gson g = new Gson();
	
	public static void parseJson(String json) {
		Application._musicFestivals = parseMusicFestivals(json);
		Application._recordLabels = parseRecordLabels(Application._musicFestivals);
	}
	
	public static MusicFestival[] parseMusicFestivals(String json) {
		return g.fromJson(json, MusicFestival[].class);
	}
	
	public static RecordLabel[] parseRecordLabels(MusicFestival[] festivals) {
		HashMap<String, RecordLabel> recordLabels = new HashMap<String, RecordLabel>();
		for (MusicFestival festival : festivals) {
			if (festival.getBands() != null) {
				for (Band band : festival.getBands()) {
					if (band.getFestivals() == null) {
						band.setFestivals(new ArrayList<MusicFestival>());
					}
					band.getFestivals().add(festival);
					String rlName = band.getRecordLabel();
					if (rlName != null && !rlName.equals("")) {
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
		
		return recordLabels.values().toArray(new RecordLabel[0]);
	}
}