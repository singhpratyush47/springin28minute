package com.springrestful.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.springrestful.domain.User;

@Component
public class UserRepo {

	private static List<User> users=new ArrayList<User>();
	static {
		users.add(new User(1,"Pratyush Singh",new Date()));
		users.add(new User(2,"Shri Ram",new Date()));
		users.add(new User(3,"Krishna",new Date()));
	}
	
	public List<User> getAllUsers(){
		return users;
	}
	
	public User saveUser(User user) {
		if(user.getId()==null) {
			Integer id=users.size()+1;
			user.setId(id);
		}
		UserRepo.users.add(user);
		return user;
	}
	
	public User findById(Integer id) {
		for(User user:users) {
			if(user.getId().equals(id)) {
				return user;
			}
		}
		return null;
	}
}
