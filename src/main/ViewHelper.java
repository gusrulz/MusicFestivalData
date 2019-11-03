package main;

public class ViewHelper {
	public static void displayData() {
		RecordLabel[] labels = Application._recordLabels;
		
		System.out.println(buildDisplay(labels));
	}
	
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