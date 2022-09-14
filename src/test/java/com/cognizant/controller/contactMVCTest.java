package com.cognizant.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cognizant.entity.Person;
import com.cognizant.service.ContactService;
import com.fasterxml.jackson.databind.ObjectMapper;

@TestMethodOrder(OrderAnnotation.class)
@ComponentScan(basePackages =  "com.cognizant")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest
public class contactMVCTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Mock
	ContactService contactService;
	
	@InjectMocks
	ContactController contactController;
	
	
	
	List<Person> myContact;
	Person person;
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(contactController).build();
	}
	
	@Test
	@Order(1)
	public void test_getAllContacts() throws Exception {
		
		myContact = new ArrayList<>();
		myContact.add(new Person(1, "Shubham", "7018320855"));
		myContact.add(new Person(2, "Aman", "8988044039"));
		myContact.add(new Person(3, "Sahil", "9459398737"));
		
		when(contactService.getAllContacts()).thenReturn(myContact);
		
		this.mockMvc.perform(get("/contact/retrieve/all"))
			.andExpect(status().isOk())
			.andDo(print());
		
	}
	
	
	@Test
	@Order(2)
	public void test_getContactById() throws Exception{
		person = new Person(2,"shubham","7018320855");
		int contactId = 2;
//		Mock
		when(contactService.getContactById(contactId)).thenReturn(person);
		
		this.mockMvc.perform(get("/contact/retrieve/{id}",contactId))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath(".id").value(2))
			.andExpect(MockMvcResultMatchers.jsonPath(".name").value("shubham"))
			.andExpect(MockMvcResultMatchers.jsonPath(".mobile").value("7018320855"))
			.andDo(print());
	}
	
	@Test
	@Order(3)
	public void test_getContactByName() throws Exception{
		myContact = new ArrayList<>();
		myContact.add(new Person(1, "shubham", "7018320855"));

		String name = "shubham";
		
//		Mock
		when(contactService.findByName(name)).thenReturn(myContact);
		
		this.mockMvc.perform(get("/contact/name/{name}",name))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath(".id").value(1))
			.andExpect(MockMvcResultMatchers.jsonPath(".name").value("shubham"))
			.andExpect(MockMvcResultMatchers.jsonPath(".mobile").value("7018320855"))
			.andDo(print());	
	}
	
	@Test
	@Order(3)
	public void test_addContact() throws Exception{
		person = new Person(2,"shubham","7018320855");
		
//		Mock
		when(contactService.SaveContact(person)).thenReturn(person);
		
//		convert java object to JSON because mockMVC ACCEPT only JSON.
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonbody = objectMapper.writeValueAsString(person);
		
		
		this.mockMvc.perform(post("/contact/save")
								.content(jsonbody)
								.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(MockMvcResultMatchers.jsonPath(".id").value(2))
			.andExpect(MockMvcResultMatchers.jsonPath(".name").value("shubham"))
			.andExpect(MockMvcResultMatchers.jsonPath(".mobile").value("7018320855"))
			.andDo(print());
	}
	
	
	@Test
	@Order(4)
	public void test_deleteContactById() throws Exception{
		person = new Person(3,"shubham","7018320855");
		int contactId = 3;
//		Mock
		when(contactService.DeleteContact(contactId)).thenReturn("User Deleted with ID : "+contactId);
		
		this.mockMvc.perform(delete("/contact/delete/{id}",contactId))
			.andExpect(status().isOk())
			.andDo(print());
	}
	
//	@Test
//	@Order(5)
//	public void test_updateContact() throws Exception{
//		
//		person = new Person(2,"Anand","8988044039");
//		Optional<Person> person1 = Optional.ofNullable(new Person(2,"Anand","8988044039"));
//		int contactId=2;
////		Mock
//		when(contactService.UpdateContact(contactId, person)).thenReturn(person);
//		
//		ObjectMapper objectMapper = new ObjectMapper();
//		String jsonbody = objectMapper.writeValueAsString(person);
//		
//		this.mockMvc.perform(put("/update/{id}",contactId)
//				.content(jsonbody)
//				.contentType(MediaType.APPLICATION_JSON))
//			.andExpect(status().isOk())
//			.andExpect(MockMvcResultMatchers.jsonPath(".name").value("Anand"))
//			.andExpect(MockMvcResultMatchers.jsonPath(".mobile").value("8988044039"))
//			.andDo(print());
//	}
	
	
	
	
}
