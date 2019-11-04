package main;

import java.util.ArrayList;

public class ViewHelper {
	
	// Prints the desired output
	public static void displayData(ArrayList<RecordLabel> labels) {
		System.out.println(buildDisplay(labels));
	}
	
	// Iterates over each RecordLabel and appends the data to a StringBuilder in the format:
	// RecordLabel
	// 		Band
	// 			Festival
	public static String buildDisplay(ArrayList<RecordLabel> labels) {
		StringBuilder sb = new StringBuilder();
		labels.sort((l1,l2) -> l1.getName().compareToIgnoreCase(l2.getName()));
		for (RecordLabel label : labels) {
			sb.append(label.getName() + "\n");
			if (label.getBands() != null) {
				ArrayList<Band> bands = label.getBands();
				bands.sort((b1,b2) -> b1.getName().compareToIgnoreCase(b2.getName()));
				for (Band band : bands) {
					sb.append("\t" + band.getName() + "\n");
					if (band.getFestivals() != null) {
						ArrayList<MusicFestival> festivals = band.getFestivals();
						festivals.sort((f1,f2) -> f1.getName().compareToIgnoreCase(f2.getName()));
						for (MusicFestival festival : festivals) {
							String festivalName = festival.getName();
							if (festivalName != null && !festivalName.equals("")) {
								sb.append("\t\t" + festival.getName() + "\n");
							}
						}
					}
				}
			}
		}
		
		return sb.toString();
	}
}