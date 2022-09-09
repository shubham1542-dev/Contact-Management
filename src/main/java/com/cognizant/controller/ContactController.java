package com.cognizant.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.entity.Person;
import com.cognizant.service.ContactService;

@RestController
@RequestMapping("/contact")
public class ContactController {

	@Autowired
	ContactService contactService;
	

	@PostMapping("/save")
	public ResponseEntity<Person> SaveContact(@RequestBody @Valid Person person){
		return ResponseEntity.status(HttpStatus.CREATED).body(this.contactService.SaveContact(person));
	}
	
	@GetMapping("/retrieve/{id}")
	public ResponseEntity<Person> getContactById(@PathVariable Integer id){
		return ResponseEntity.status(HttpStatus.OK).body(this.contactService.getContactById(id));
	}
	
	
//	Optional EndPoints
	
	@GetMapping("retrieve/all")
	public List<Person> getAllContacts(){
		return this.contactService.getAllContacts();
	}
	
	@PutMapping("/update/{id}")
	public Person updateContact(@PathVariable Integer id, @RequestBody @Valid Person person){
		return this.contactService.UpdateContact(id, person);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteContact(@PathVariable Integer id) {
		return this.contactService.DeleteContact(id);
	}
	
	@GetMapping("/name/{name}")
	public List<Person> getByName(@PathVariable String name){
		return this.contactService.findByName(name);
	}
	
}
