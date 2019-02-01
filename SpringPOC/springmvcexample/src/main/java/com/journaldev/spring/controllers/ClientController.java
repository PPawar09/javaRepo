package com.journaldev.spring.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.journaldev.spring.model.SessionData;
import com.journaldev.spring.model.User;

@Controller
public class ClientController {
	
	@Autowired
	SessionData sessionData;
	
	@RequestMapping(value = "/client", method = RequestMethod.GET)
	public ModelAndView showOtsCleint(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("***sessionData.isUserLogin()****"+sessionData.isUserLogin());
		ModelAndView mav = new ModelAndView("clientOTS");
		mav.addObject("isUserLogin",sessionData.isUserLogin());
		return mav;
	}

}
