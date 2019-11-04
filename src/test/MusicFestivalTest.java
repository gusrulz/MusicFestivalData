package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import main.Band;
import main.ConnectionHelper;
import main.JsonHelper;
import main.MusicFestival;
import main.RecordLabel;
import main.ViewHelper;

public class MusicFestivalTest {

	@Test
	public void connectionHelperTest() {
		Assert.assertNotNull(ConnectionHelper.getResponse("http://eacodingtest.digital.energyaustralia.com.au/api/v1/festivals"));
	}
	
	@Test
	public void parseFestivalJsonTest() {
		try {
			String json = IOUtils.toString(this.getClass().getResourceAsStream("SamplePayload.json"), StandardCharsets.UTF_8);
			Assert.assertNotNull(JsonHelper.parseMusicFestivals(json));
		} catch (IOException e) {
			fail("IO Exception thrown loading 'SamplePayload.json'");
		}
	}
	
	@Test
	public void displayDataTest() {
		final String EXPECTED = "Record Label 1\n\tBand X\n\t\tOmega Festival\n\tBand Y\nRecord Label 2\n\tBand A\n\t\tAlpha Festival\n\t\tBeta Festival\n";
		
		RecordLabel label1 = new RecordLabel();
		label1.setName("Record Label 1");
		RecordLabel label2 = new RecordLabel();
		label2.setName("Record Label 2");
		
		ArrayList<RecordLabel> labels = new ArrayList<RecordLabel>();
		labels.add(label2);
		labels.add(label1);
		
		Band bandA = new Band();
		bandA.setName("Band A");
		Band bandX = new Band();
		bandX.setName("Band X");
		Band bandY = new Band();
		bandY.setName("Band Y");
		
		MusicFestival alphaFestival = new MusicFestival();
		alphaFestival.setName("Alpha Festival");
		MusicFestival betaFestival = new MusicFestival();
		betaFestival.setName("Beta Festival");
		MusicFestival omegaFestival = new MusicFestival();
		omegaFestival.setName("Omega Festival");
		
		ArrayList<Band> label1Bands = new ArrayList<Band>(); 
		label1Bands.add(bandY);
		label1Bands.add(bandX);
		label1.setBands(label1Bands);
		
		ArrayList<Band> label2Bands = new ArrayList<Band>();
		label2Bands.add(bandA);
		label2.setBands(label2Bands);
		
		ArrayList<MusicFestival> bandAFestivals = new ArrayList<MusicFestival>(); 
		bandAFestivals.add(betaFestival);
		bandAFestivals.add(alphaFestival);
		bandA.setFestivals(bandAFestivals);
		
		ArrayList<MusicFestival> bandXFestivals = new ArrayList<MusicFestival>(); 
		bandXFestivals.add(omegaFestival);
		bandX.setFestivals(bandXFestivals);
		
		String output = ViewHelper.buildDisplay(labels);
		Assert.assertEquals(EXPECTED, output);
	}
	
	@Test
	public void parseRecordLabelTest() {
		Band bandA = new Band();
		bandA.setName("Band A");
		bandA.setRecordLabel("Record Label 2");
		Band bandX = new Band();
		bandX.setName("Band X");
		bandX.setRecordLabel("Record Label 1");
		Band bandY = new Band();
		bandY.setName("Band Y");
		bandY.setRecordLabel("Record Label 1");
		
		MusicFestival alphaFestival = new MusicFestival();
		alphaFestival.setName("Alpha Festival");
		alphaFestival.setBands(new ArrayList<Band>());
		alphaFestival.getBands().add(bandA);
		MusicFestival betaFestival = new MusicFestival();
		betaFestival.setName("Beta Festival");
		betaFestival.setBands(new ArrayList<Band>());
		betaFestival.getBands().add(bandA);
		MusicFestival omegaFestival = new MusicFestival();
		omegaFestival.setName("Omega Festival");
		omegaFestival.setBands(new ArrayList<Band>());
		omegaFestival.getBands().add(bandX);
		
		MusicFestival[] festivals = {alphaFestival, betaFestival, omegaFestival};
		ArrayList<RecordLabel> recordLabels = JsonHelper.parseRecordLabels(festivals);
		
		Assert.assertEquals(2, recordLabels.size());
		Assert.assertNotNull(recordLabels.get(0).getBands());
		Assert.assertNotNull(recordLabels.get(0).getBands().get(0).getFestivals());
	}
}