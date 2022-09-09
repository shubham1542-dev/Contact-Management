package com.cognizant.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
	
	@NotNull(message = "name shouldn't be null ")
	@Size(min=2, message="user name should have at least 2 characters")
	private String name;
	
	
	@Pattern(regexp = "^\\d{10}$",message="invalid mobile number")
	private String mobile;

}
