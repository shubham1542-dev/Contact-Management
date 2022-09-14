package com.cognizant.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cognizant.entity.Person;
import com.cognizant.service.ContactService;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class contactControllerTest {

	@Mock
	ContactService contactService;
	
	@InjectMocks
	ContactController contactController;
	
	List<Person> myContact;
	Person person;
	
	@Test
	public void getAllContact() {
		myContact = new ArrayList<>();
		myContact.add(new Person(1, "Shubham", "7018320855"));
		myContact.add(new Person(2, "Aman", "8988044039"));
		myContact.add(new Person(3, "Sahil", "9459398737"));
		
		when(contactService.getAllContacts()).thenReturn(myContact);//Mocking
		List<Person> allContacts = contactController.getAllContacts();
		assertEquals(3, allContacts.size());
		assertEquals("Aman", allContacts.get(1).getName());
	}
	
	
	@Test
	public void getContactById() {
		person = new Person(1,"shubham","7018320855");
		
		when(contactService.getContactById(1)).thenReturn(person);
		
		ResponseEntity<Person> response = contactController.getContactById(1);
		assertEquals(1, response.getBody().getId());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void getContactByName() {
		myContact = new ArrayList<>();
		myContact.add(new Person(1, "shubham", "7018320855"));
		myContact.add(new Person(2, "shubham", "8988044039"));
		
		
		String Name = "shubham";
		when(contactService.findByName(Name)).thenReturn(myContact);
		List<Person> Response = contactController.getByName(Name);
		assertEquals(2, Response.size());
		assertEquals("7018320855", Response.get(0).getMobile());
	}
	
	@Test
	public void saveContact() {
		person = new Person(1,"shubham","7018320855");
		
		when(contactService.SaveContact(person)).thenReturn(person);
		ResponseEntity<Person> saveContact = contactController.SaveContact(person);
		assertEquals(HttpStatus.CREATED, saveContact.getStatusCode());
		assertEquals("7018320855", saveContact.getBody().getMobile());
	}
	
}
