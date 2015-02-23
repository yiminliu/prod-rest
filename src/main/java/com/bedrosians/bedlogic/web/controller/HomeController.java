package com.bedrosians.bedlogic.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bedrosians.bedlogic.exception.DatabaseOperationException;
import com.bedrosians.bedlogic.exception.DatabaseSchemaException;

public class HomeController {

	@RequestMapping(value = { "/", "/.", "/home**", "/welcome**" }, method = RequestMethod.GET)
	public String welcomePage() {
		return "login.jsp";
 
	}
 
	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {
 
		ModelAndView model = new ModelAndView();
		model.addObject("title", "IMS Login Form");
		model.addObject("message", "This is protected page!");
		model.setViewName("admin");
 
		return model;
 
	}
 
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
		                      @RequestParam(value = "logout", required = false) String logout,
		                      HttpSession session) {
 
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}
 
		if (logout != null) {
			session.invalidate();
			model.addObject("message", "You've been logged out successfully.");
		}
		model.setViewName("ims/index");
 
		return model;
 
	}
	
	@ExceptionHandler(DatabaseSchemaException.class)
	 	public ModelAndView handleDatabaseSchemaException(DatabaseSchemaException ex) {
	  
	 		ModelAndView model = new ModelAndView("/exception/exception");
	 		model.addObject("errorCode", ex.getHttpErrorCode());
	 		model.addObject("errorType", ex.getErrorType());
	 		model.addObject("errorMessage", ex.getMessage());
	 		model.addObject("rootErrorMessage", ex.getRootErrorMessage());
	 		model.addObject("error", ex);
	 		model.addObject("rootError", ex.getRootError());
	  
	 		return model;
	}
	
	@ExceptionHandler(DatabaseOperationException.class)
	public ModelAndView handleDataOperationException(DatabaseOperationException ex) {
 
		ModelAndView model = new ModelAndView("/exception/exception");
		model.addObject("errorCode", ex.getHttpErrorCode());
 		model.addObject("errorType", ex.getErrorType());
 		model.addObject("errorMessage", ex.getMessage());
		model.addObject("error", ex.getRootError());
 
		return model;
 	}
 
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
 
		ModelAndView model = new ModelAndView("error/generic_error");
		model.addObject("errorMessage", "this is Exception.class");
 
		return model;
 
	}
 
}

