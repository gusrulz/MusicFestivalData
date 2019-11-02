package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

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
		
		RecordLabel[] labels = {label1, label2};
		
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
		
		Band[] label1Bands = {bandX, bandY};
		label1.setBands(label1Bands);
		
		Band[] label2Bands = {bandA};
		label2.setBands(label2Bands);
		
		MusicFestival[] bandXFestivals = {omegaFestival};
		bandX.setFestivals(bandXFestivals);
		
		MusicFestival[] bandAFestivals = {alphaFestival, betaFestival};
		bandA.setFestivals(bandAFestivals);
		
		String output = ViewHelper.buildDisplay(labels);
		Assert.assertEquals(EXPECTED, output);
	}
}