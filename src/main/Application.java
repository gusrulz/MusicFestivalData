package main;

public class Application {
	public static void main(String[] arguments) {
		System.out.println("Calling Festival API...");
		String json = ConnectionHelper.getResponse("http://eacodingtest.digital.energyaustralia.com.au/api/v1/festivals");
		
		if (json == null) {
			System.out.println("Failed to download data. Exiting...");
			return;
		} else {
			System.out.println("Successfully downloaded the following JSON payload:");
			System.out.println(json);
		}
		
		System.out.println("Parsing JSON...");
		RecordLabel[] labels = JsonHelper.parseJson(json);
		
		System.out.println("Displaying Data...");
		ViewHelper.displayData(labels);
		
		System.out.println("Exiting...");
	}
}