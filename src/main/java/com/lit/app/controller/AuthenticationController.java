package com.lit.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AuthenticationController {

	@RequestMapping(value="/accessDenied")
	public String accessDenied(ModelMap m){
		m.addAttribute("msg","You are not authorized to access this page");
		return "errors/accessDenied";
	}
	
	@RequestMapping(value="/login")
	public String login(ModelMap m){
		return "login";
	}
	
}
