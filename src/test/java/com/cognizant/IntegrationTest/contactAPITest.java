package com.cognizant.IntegrationTest;



import org.json.JSONException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.cognizant.entity.Person;

import lombok.extern.slf4j.Slf4j;


@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
@Slf4j
public class contactAPITest {
	
	
	@Test
	@Order(1)
	public void test_getAllContacts() throws JSONException {
		
		String expected = """
				[
					{
						"id": 4,
						"name": "Sangeet",
						"mobile": "8899657425"
					},
					{
						"id": 5,
						"name": "shikha",
						"mobile": "1274567891"
					},
					{
						"id": 6,
						"name": "Akash",
						"mobile": "8977055737"
					}
				]
				""";
		
		TestRestTemplate restTemplate = new TestRestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/contact/retrieve/all", String.class);
		log.info(response.getBody());
		log.info(response.getStatusCode().toString());
		JSONAssert.assertEquals(expected, response.getBody(), false);
		
	}
	
	
	@Test
	@Order(2)
	public void test_getContactById() throws JSONException {
		
		String expected = """
					{
						"id": 4,
						"name": "Sangeet",
						"mobile": "8899657425"
					}
				""";
		
		TestRestTemplate restTemplate = new TestRestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/contact/retrieve/4", String.class);
		log.info(response.getBody());
		log.info(response.getStatusCode().toString());
		JSONAssert.assertEquals(expected, response.getBody(), false);
		
	}
	
	@Test
	@Order(3)
	public void test_getContactByName() throws JSONException {
		
		String expected = """
					[
						{
							"id": 5,
							"name": "shikha",
							"mobile": "1274567891"
						}
					]
				""";
		
		TestRestTemplate restTemplate = new TestRestTemplate();
		ResponseEntity<String> respose = restTemplate.getForEntity("http://localhost:8080/contact/name/shikha", String.class);
		log.info(respose.getBody());
		log.info(respose.getStatusCode().toString());
		JSONAssert.assertEquals(expected, respose.getBody(), false);
	}
	
	@Test
	@Order(4)
	@Disabled
	public void test_addContact() throws JSONException {
		
		Person person = new Person(12,"shivam","7878451289");
		String expected = """
				{
					"id": 12,
					"name": "shivam",
					"mobile": "7878451289"
				}
			""";
		
		TestRestTemplate restTemplate = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders(); 
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Person> request = new HttpEntity<Person>(person,headers);
		
		ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/contact/save/", request, String.class);
		log.info(response.getBody());
		log.info(response.getStatusCode().toString());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	@Test
	@Order(5)
	@Disabled
	public void test_updateContact() throws JSONException {
		
		Person person = new Person(4,"shivam","7878451289");
		String expected = """
				{
					"id": 4,
					"name": "shivam",
					"mobile": "7878451289"
				}
			""";
		
		TestRestTemplate restTemplate = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders(); 
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Person> request = new HttpEntity<Person>(person,headers);
		
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/contact/update/4",HttpMethod.PUT, request, String.class);
		log.info(response.getBody());
		log.info(response.getStatusCode().toString());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	

	

}
