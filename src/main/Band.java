package main;

import java.util.ArrayList;

public class Band {
	
	public Band() {
		
	}
	
	private String name;
	private String recordLabel;
	private ArrayList<MusicFestival> festivals;
	
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
	public ArrayList<MusicFestival> getFestivals() {
		return festivals;
	}
	public void setFestivals(ArrayList<MusicFestival> festivals) {
		this.festivals = festivals;
	}
}