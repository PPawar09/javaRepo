package com.pp.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pp.service.WelcomeService;

@RestController
public class WelcomeController {
	
	@Autowired  // Auto wiring Spring will create this bean and as needed here so no new required.
	WelcomeService service;
	
	@RequestMapping("/welcome")
	public String welcome(){
		return "Welcome Message From SpringBoot updated";
	}

}


