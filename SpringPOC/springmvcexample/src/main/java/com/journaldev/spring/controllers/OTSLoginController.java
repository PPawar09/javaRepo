package com.journaldev.spring.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.journaldev.spring.model.User;

@Controller
public class OTSLoginController {

	@RequestMapping(value = "/otslogin", method = RequestMethod.GET)
	public ModelAndView showOtsLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("loginOTS");
		mav.addObject("user", new User());
		return mav;
	}
	
	@RequestMapping(value = "/otshome", method = RequestMethod.GET)
	public ModelAndView showOtshomePage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("homePageOTS");
		mav.addObject("user", new User());
		return mav;
	}

}
