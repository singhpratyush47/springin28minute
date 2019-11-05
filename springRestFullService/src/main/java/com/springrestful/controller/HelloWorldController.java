package com.springrestful.controller;

import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrestful.domain.User;
import com.springrestful.exception.UserNotFoundException;
import com.springrestful.repositories.UserJPARepo;
import com.springrestful.repositories.UserRepo;

@RestController
@RequestMapping("/api/v1/")
public class HelloWorldController {

	@Autowired
	private UserRepo repo;
	@Autowired
	private EntityManager em;
	@Autowired
	private UserJPARepo jpaRepo;
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/helloWorld/")
	public String helloWorld() {
		return String.format("%s", "Hello World");
		
	}
	
	@GetMapping("/helloWorld/path-variable/{name}")
	public String helloWorldPathVariable(@PathVariable String name) {
		return String.format("Hello World %s", name);
		
	}
	
	@GetMapping("/getAllUsers/")
	public List<User> getAllUser() {
		return repo.getAllUsers();
	}
	
	@GetMapping("/getAllUsers/Jpa")
	public List<User> getAllUserJpa() {
		TypedQuery<User> tq= em.createNamedQuery("UserTestNameQuery", User.class);
		List<User> listofUsers= tq.getResultList();
		return listofUsers;
	}

	@GetMapping("/getAllUsers/Jpa/parameter")
	public List<User> getAllUserParameters() {
		TypedQuery<User> query =em.createQuery("SELECT u FROM User u WHERE u.name = :name",
				User.class);
		List<User> listOfUser= query.setParameter("name", "Ram").getResultList();
    return listOfUser;
	}
	
	@GetMapping("/getAllUsers/Jpa/JPQL")
	public List<String> getAllUserJPQL() {
		TypedQuery<String> query =em.createQuery("SELECT u.name FROM User u",
				String.class);
		List<String> listOfUserName= query.setParameter("name", "Ram").getResultList();
    return listOfUserName;
	}
	
	@GetMapping("/getAllUsers/Jpa/NativeSQlQuery")
	public List<String> getAllUserNativeSQLQuery() {
		TypedQuery<String> query =em.createQuery("SELECT u.name FROM User u",
				String.class);
		List<String> listOfUserName= query.setParameter("name", "Ram").getResultList();
    return listOfUserName;
	}
	
	@GetMapping("/getAllUsers/Criteria")
	public List<User> getAllUserCriteriaJPA(){
		 CriteriaBuilder cb = em.getCriteriaBuilder();
		  CriteriaQuery<User> q = cb.createQuery(User.class);
		  Root<User> c = q.from(User.class);
		  q.select(c);
		  TypedQuery<User> query = em.createQuery(q);
		  List<User> listofUsers = query.getResultList();
		  return listofUsers;
	}
	
	@GetMapping("/getUser/{id}")
	public Resource<User> getUserById(@PathVariable Integer id) {
		User user=repo.findById(id);
		if(user==null) {
			throw new UserNotFoundException("id-"+id);
		}
		Resource<User> resource=new Resource<User>(user);
		ControllerLinkBuilder linkBuilder=ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllUser());
		resource.add(linkBuilder.withRel("all-users"));
		return resource;
	}
	
	@PostMapping("/saveUser/")
	public User postUser(@Valid @RequestBody User user) {
		return repo.saveUser(user);
	}
	
	@GetMapping(path="/hello-world-internationalized")
	public String helloWorldInternationalized() {
		return messageSource.getMessage("good.morning.message", null,LocaleContextHolder.getLocale());
	}
}
