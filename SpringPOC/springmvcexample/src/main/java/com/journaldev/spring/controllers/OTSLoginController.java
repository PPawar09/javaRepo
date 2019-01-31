package com.journaldev.spring.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.journaldev.spring.model.Login;
import com.journaldev.spring.model.User;
import com.journaldev.spring.service.UserService;

@Controller
public class OTSLoginController {
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "/otslogin", method = RequestMethod.GET)
	public ModelAndView showOtsLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("loginOTS");
		mav.addObject("user", new User());
		return mav;
	}
	
	@RequestMapping(value = "/otsloginsubmit", method = RequestMethod.POST)
	public ModelAndView otsLoginSubmit(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("loginDto") Login user) {

		ModelAndView mav = null;

		if(null != userService.validateUser(user)){
			mav = new ModelAndView("homePageOTS");
		}else{
			mav = new ModelAndView("loginOTS"); // redirect to login page with Error Message
			Login login = new Login();
			login.setLoginError("Username or Password is wrong!!");
			mav.addObject("loginDto",login);
		}

		return mav;
	}
	
	
	
	@RequestMapping(value = "/otshome", method = RequestMethod.GET)
	public ModelAndView showOtshomePage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("homePageOTS");
		mav.addObject("user", new User());
		return mav;
	}

}
