package com.springrestful.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Details of Users")
@Entity
@NamedQuery(name="UserTestNameQuery",query="select u from User u")
//@NamedQuery(name="UserNameQueryWithParameter",query="SELECT u FROM User u WHERE u.name = :name")
public class User {

	@Id
	@GeneratedValue
	private Integer id;
	@Size(min=2,message="name should have at least two characters")
	@ApiModelProperty(notes="Name should have atleast 2 characters")
	private String name;
	@Past
	@ApiModelProperty(notes="Birth date should be in past")
	private Date dateOfBirth;
	public User(Integer id, String name, Date dateOfBirth) {
		super();
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}
	public User() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + "]";
	}
}
