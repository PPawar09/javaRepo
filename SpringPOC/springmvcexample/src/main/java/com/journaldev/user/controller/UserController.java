package com.journaldev.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.journaldev.spring.model.User;

@Controller
public class UserController {
	
	 	@RequestMapping(value = "/userregistration", method = RequestMethod.GET)
	    public String registration(Model model) {
	        model.addAttribute("userForm", new User());

	        return "registration";
	    }

	    @RequestMapping(value = "/userregistration", method = RequestMethod.POST)
	    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
	        //userValidator.validate(userForm, bindingResult);

	        if (bindingResult.hasErrors()) {
	            return "registration";
	        }

	        //userService.save(userForm);

	        //securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

	        return "redirect:/welcome";
	    }

	    @RequestMapping(value = "/userlogin", method = { RequestMethod.GET, RequestMethod.POST })
	    public String login(Model model, String error, String logout) {
	        if (error != null)
	            model.addAttribute("error", "Your username and password is invalid.");

	        if (logout != null)
	            model.addAttribute("message", "You have been logged out successfully.");

	        return "userlogin";
	    }

	    @RequestMapping(value = {"/usersubmit", "/welcome"}, method = { RequestMethod.GET, RequestMethod.POST })
	    public String welcome(Model model) {
	        return "userwelcome";
	    }

}
