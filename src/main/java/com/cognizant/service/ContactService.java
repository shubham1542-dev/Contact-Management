package com.cognizant.service;

import java.util.List;

import com.cognizant.entity.Person;

public interface ContactService {
	
	Person SaveContact(Person person);
	
	Person getContactById(Integer id);
	
	List<Person> getAllContacts();
	
	Person UpdateContact(Integer id, Person person);
	
	String DeleteContact(Integer id);
	
	List<Person> findByName(String name);

}
