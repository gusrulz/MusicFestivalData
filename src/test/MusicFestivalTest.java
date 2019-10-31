package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import main.ConnectionHelper;
import main.JsonHelper;

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
}