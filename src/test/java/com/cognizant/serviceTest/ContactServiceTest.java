package com.cognizant.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.entity.Person;
import com.cognizant.repository.ContactRepository;
import com.cognizant.service.ContactServiceImpl;

@SpringBootTest
public class ContactServiceTest {

	@Mock
	ContactRepository contactRepository;

	@InjectMocks
	ContactServiceImpl contactService;

	public List<Person> myContact;

	@Test
	@Order(1)
	public void test_getAllContacts() {
		myContact = new ArrayList<>();
		myContact.add(new Person(1, "Shubham", "7018320855"));
		myContact.add(new Person(2, "Aman", "8988044039"));
		myContact.add(new Person(3, "Sahil", "9459398737"));

//		Mockito
		when(contactRepository.findAll()).thenReturn(myContact);
		assertThat(contactService.getAllContacts().size()).isEqualTo(3);
	}

	@Test
	@Order(2)
	public void test_getcontactById() {
		Person person = new Person(1, "Shubham", "7018320855");
		when(contactRepository.findById(1)).thenReturn(Optional.of(person));
		assertThat(contactService.getContactById(1).getMobile()).isEqualTo("7018320855");
	}

	@Test
	@Order(3)
	public void test_getContactByName() {
		myContact = new ArrayList<>();
		myContact.add(new Person(1, "shubham", "7018320855"));
		myContact.add(new Person(2, "shubham", "8988044039"));

		when(contactRepository.findByName("shubham")).thenReturn(myContact);
		assertThat(contactService.findByName("shubham").size()).isEqualTo(2);
	}
	

}
