package com.springrestful.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Person {
		@Id
	   private Integer id;
	   private String email;
	   private String name;
	   private String password;
	   @OneToOne(mappedBy="person")
	   private PersonProfile profile;
}
