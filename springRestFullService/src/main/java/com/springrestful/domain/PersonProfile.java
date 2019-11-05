package com.springrestful.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class PersonProfile {

	 	@Id
	   private Integer id;
	   private int age;
	   private String gender;
	   private String favoriteColor;
	   @OneToOne
	   private Person person;
}
