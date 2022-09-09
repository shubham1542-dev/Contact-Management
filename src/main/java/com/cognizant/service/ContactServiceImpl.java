package com.cognizant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.entity.Person;
import com.cognizant.exception.DataAlreayExistException;
import com.cognizant.exception.UserNotFoundException;
import com.cognizant.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	ContactRepository contactRepository;

	@Override
	public Person SaveContact(Person person) {

		List<String> allPhoneNumber = this.contactRepository.getAllPhoneNumber();
		if (allPhoneNumber.contains(person.getMobile())) {
			throw new DataAlreayExistException("Phone Number Already Exist");
		}
		return this.contactRepository.save(person);
	}

	@Override
	public Person getContactById(Integer id) {
		Person person = this.contactRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User Not Found With Id: " + id));
		return person;
	}

	@Override
	public List<Person> getAllContacts() {

		List<Person> all = this.contactRepository.findAll();
		if (all.isEmpty()) {
			throw new UserNotFoundException("No Data Found");
		}
		return all;
	}

	@Override
	public Person UpdateContact(Integer id, Person person) {
		Person OldData = this.contactRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User Not Found With Id: " + id));

		List<String> allPhoneNumber = this.contactRepository.getAllPhoneNumber();
		if (allPhoneNumber.contains(person.getMobile())) {
			throw new DataAlreayExistException("Phone Number Already Exist");
		}
		OldData.setName(person.getName());
		OldData.setMobile(person.getMobile());
		Person updatedResult = this.contactRepository.save(OldData);
		return updatedResult;
	}

	@Override
	public String DeleteContact(Integer id) {
		this.contactRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User Not Found With Id: " + id));
		this.contactRepository.deleteById(id);
		return "User Deleted with ID : " + id;
	}

	@Override
	public List<Person> findByName(String name) {

		List<Person> findByName = this.contactRepository.findByName(name);
		if (findByName.isEmpty()) {
			throw new UserNotFoundException("User Not Found With Name: " + name);
		}
		return findByName;
	}

}