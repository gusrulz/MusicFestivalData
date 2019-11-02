package main;

public class Band {
	
	public Band() {
		
	}
	
	private String name;
	private String recordLabel;
	private MusicFestival[] festivals;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRecordLabel() {
		return recordLabel;
	}
	public void setRecordLabel(String recordLabel) {
		this.recordLabel = recordLabel;
	}
	public MusicFestival[] getFestivals() {
		return festivals;
	}
	public void setFestivals(MusicFestival[] festivals) {
		this.festivals = festivals;
	}
}