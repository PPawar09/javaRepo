package com.journaldev.spring.dao;

import com.journaldev.spring.model.Login;
import com.journaldev.spring.model.User;

public interface UserDao {
	
	void register(User user);
	
	Login validateUser(Login login);
	
}
