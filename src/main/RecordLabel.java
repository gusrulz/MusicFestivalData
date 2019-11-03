package main;

import java.util.ArrayList;

public class RecordLabel {
	public RecordLabel() {
		
	}
	
	private String name;
	private ArrayList<Band> bands;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Band> getBands() {
		return bands;
	}
	public void setBands(ArrayList<Band> bands) {
		this.bands = bands;
	}
}