package com.journaldev.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.journaldev.spring.dao.UserDaoImpl;
import com.journaldev.spring.model.Login;
import com.journaldev.spring.model.User;
import com.journaldev.spring.model.UserTaskRecord;

public class UserService {

	@Autowired
	UserDaoImpl daoImpl;
	
	public void register(User user){
		daoImpl.register(user);
	}
	
	public boolean register(Login login){
		return daoImpl.registerUser(login);
	}
	
	public Login validateUser(Login login){
		Login user = null;
		try{
			user = daoImpl.validateUser(login);
		}catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}
	
	public List<UserTaskRecord> getTaskByUserId(String userId){
		List<UserTaskRecord> utrlist = null;
		try{
			utrlist = daoImpl.getTaskById(userId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return utrlist;
	}
	
}
