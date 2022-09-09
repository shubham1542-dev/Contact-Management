package com.cognizant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.entity.Person;

@Repository
public interface ContactRepository extends JpaRepository<Person, Integer>{

	List<Person> findByName(String name);
	
	@Query(value="SELECT mobile from person",nativeQuery=true)
	public List<String> getAllPhoneNumber();


}
