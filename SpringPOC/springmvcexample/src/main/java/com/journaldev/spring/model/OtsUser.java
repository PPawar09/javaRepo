package com.journaldev.spring.model;

import java.io.Serializable;

public class OtsUser implements Serializable{
	
	private String id;
	private String emailId;
	private String name;
	private String userId;
	private String password;
	private String confPwd;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfPwd() {
		return confPwd;
	}
	public void setConfPwd(String confPwd) {
		this.confPwd = confPwd;
	}
	
	
	
	

}
