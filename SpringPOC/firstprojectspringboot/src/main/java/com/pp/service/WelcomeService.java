package com.pp.service;

import org.springframework.stereotype.Component;


// Spring to manage this bean and create instance of this.We also want to inject this instance where needed called autowire
@Component
public class WelcomeService{
	
	public String getMessage(){
		return "Welcome Message From SpringBoot";
	}
	
}