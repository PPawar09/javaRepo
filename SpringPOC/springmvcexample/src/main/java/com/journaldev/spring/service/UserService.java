package com.journaldev.spring.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.journaldev.spring.dao.UserDaoImpl;
import com.journaldev.spring.model.Login;
import com.journaldev.spring.model.User;

public class UserService {

	@Autowired
	UserDaoImpl daoImpl;
	
	public void register(User user){
		daoImpl.register(user);
	}
	
	public User validateUser(Login login){
		User user = null;
		try{
			user = daoImpl.validateUser(login);
		}catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}
}
