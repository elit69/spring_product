package com.lit.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lit.app.entities.University;
import com.lit.app.entities.User;
import com.lit.app.services.UniversityService;

@Controller /*
			 * Indicates that an annotated class is a "Controller" of Spring
			 * Framework (a web controller)
			 */
@RequestMapping("/")
/*
 * is used to map the user’s requests to handler classes or methods. It can be
 * applied on the class level and also on the method level.
 */
public class MainController {

	
	/*
	 * @Autowired, @Inject, and @Resource tell the application context to inject
	 * the beans.
	 * 
	 * @Autowired and @Inject works very similar but @Resource has bit
	 * different.
	 */
	
	@Autowired
	@Qualifier("firstName")
	String firstname;
	
	@Inject
	@Qualifier("lastName")
	String lastName;
	
	@Resource
	@Qualifier("gender")
	String gender;
	
	@RequestMapping(value="/")
	public String homePahe(){
		System.out.println(firstname + " " + lastName + " " + gender);
		return "";
	}
	
	@Autowired
	UniversityService universityService;
	
	@RequestMapping(value="/university")
	public String getUni(@RequestParam(required=false , defaultValue="") String universityName,ModelMap m){
		List<University> a = (ArrayList<University>) universityService.findAllUniversityByName(universityName);
		m.addAttribute("university",a);
		
		Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
	    User user = (User) authentication.getPrincipal();
	    
		
		return "university";
	}
	
}
