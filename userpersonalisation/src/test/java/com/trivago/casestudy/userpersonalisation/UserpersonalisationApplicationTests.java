package com.trivago.casestudy.userpersonalisation;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserpersonalisationApplicationTests {

	@LocalServerPort
	int randomServerPort;

	public static final String USERID = "872636973412936150";

	@Test
	public void testGetAmenities() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + randomServerPort
				+ "/services/api/v1/amenities?userId=" + USERID;
		URI uri = new URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		Assert.assertEquals(200, result.getStatusCodeValue());

	}


	@Test
	public void testGetHotels() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl =
				"http://localhost:" + randomServerPort + "/services/api/v1/hotels?userId=" + USERID;
		URI uri = new URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		Assert.assertEquals(200, result.getStatusCodeValue());

	}

}
