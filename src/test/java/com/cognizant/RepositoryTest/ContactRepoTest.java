package com.cognizant.RepositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.entity.Person;
import com.cognizant.repository.ContactRepository;

@SpringBootTest
public class ContactRepoTest {
	
	@Autowired
	private ContactRepository contactRepository;
	
//	Save contact Repo Test
	
	@Test
	@Disabled
	public void saveContact() {
		Person person = new Person();
		person.setMobile("7777888899");
		person.setName("Junit Testing");
		Person save = this.contactRepository.save(person);
		assertThat(save.getId()).isGreaterThan(0);
	}
	
	@Test
	public void getAllContact() {
		List<Person> all = this.contactRepository.findAll();
		assertThat(all.size()).isGreaterThan(0);
	}
	
	@Test
	public void getContactById() {
		Person person = this.contactRepository.findById(5).get();
		assertThat(5).isEqualTo(person.getId());
	}
	
	@Test
	public void findByName() {
		List<Person> findByName = this.contactRepository.findByName("shubham");
		assertThat(findByName.size()).isGreaterThan(0);
	}
	
	@Test
	@Disabled
	public void updateContact() {
		Person person = this.contactRepository.findById(10).get();
		person.setMobile("7878787878");
		Person save = this.contactRepository.save(person);
		assertThat("7878787878").isEqualTo(save.getMobile());
	}

}
