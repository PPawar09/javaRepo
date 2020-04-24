package com.journaldev.spring.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class LoginController {
	
  private Logger log = LoggerFactory.getLogger(this.getClass());
	
  @Autowired
  UserService userService;
  
  @RequestMapping(value = "/loginNew", method = RequestMethod.GET)
  public ModelAndView showLoginNew(HttpServletRequest request, HttpServletResponse response) {
	log.info("****Inside /loginNew ****");
    ModelAndView mav = new ModelAndView("loginn");
    mav.addObject("loginn", new Login());
    return mav;
    
  }
  
  @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
  public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("loginn") Login login) {
    ModelAndView mav = null;
    User user = userService.validateUser(login);
    if (null != user) {
    mav = new ModelAndView("welcome");
    mav.addObject("firstname", user.getFirstname());
    } else {
    mav = new ModelAndView("loginn");
    mav.addObject("message", "Username or Password is wrong!!");
    }
    return mav;
  }
}