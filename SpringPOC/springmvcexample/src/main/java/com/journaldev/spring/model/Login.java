package com.journaldev.spring.model;

public class Login {
	
	private String id;
	private String loginId;
	private String firstName;
	private String lastName;
	private String loginError;
	private String emailId;
	private String password;
	private String confirmPassword;
	private boolean registerFlg;
	private boolean signUpTab;
	
	public boolean isSignUpTab() {
		return signUpTab;
	}
	public void setSignUpTab(boolean signUpTab) {
		this.signUpTab = signUpTab;
	}
	public boolean isRegisterFlg() {
		return registerFlg;
	}
	public void setRegisterFlg(boolean registerFlg) {
		this.registerFlg = registerFlg;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLoginError() {
		return loginError;
	}
	public void setLoginError(String loginError) {
		this.loginError = loginError;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
