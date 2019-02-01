package com.journaldev.spring.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.journaldev.spring.model.User;

@Controller
public class TaskController {
	
	@RequestMapping(value = "/task", method = RequestMethod.GET)
	public ModelAndView showOtsTask(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("taskOTS");
		mav.addObject("user", new User());
		return mav;
	}

}
