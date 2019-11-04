package main;

public class ViewHelper {
	
	// Prints the desired output
	public static void displayData(RecordLabel[] labels) {
		System.out.println(buildDisplay(labels));
	}
	
	// Iterates over each RecordLabel and appends the data to a StringBuilder in the format:
	// RecordLabel
	// 		Band
	// 			Festival
	public static String buildDisplay(RecordLabel[] labels) {
		StringBuilder sb = new StringBuilder();
		for (RecordLabel label : labels) {
			sb.append(label.getName() + "\n");
			if (label.getBands() != null) {
				for (Band band : label.getBands()) {
					sb.append("\t" + band.getName() + "\n");
					if (band.getFestivals() != null) {
						for (MusicFestival festival : band.getFestivals()) {
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