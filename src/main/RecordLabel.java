package main;

public class RecordLabel {
	public RecordLabel() {
		
	}
	
	private String name;
	private Band[] bands;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Band[] getBands() {
		return bands;
	}
	public void setBands(Band[] bands) {
		this.bands = bands;
	}
}