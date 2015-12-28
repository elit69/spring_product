package com.lit.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lit.app.services.ProductService;

@Controller /*
			 * Indicates that an annotated class is a "Controller" of Spring
			 * Framework (a web controller)
			 */
@RequestMapping("/")
/*
 * is used to map the user’s requests to handler classes or methods. It can be
 * applied on the class level and also on the method level.
 */
public class Main {

	@Autowired 
	ProductService productService;
	/*
	 * @Autowired, @Inject, and @Resource tell the application context to inject
	 * the beans.
	 * 
	 * @Autowired and @Inject works very similar but @Resource has bit
	 * different.
	 */
	@RequestMapping(value="/login")
	public String homePahe1(){
		return "login";
	}	
	
	@RequestMapping(value="/")
	public String homePahe(){
		System.out.println(getRole());
		return "index";
	}	
	
	@RequestMapping(value="/admin")
	public String admin(ModelMap model){
		model.addAttribute("list", productService.list());			
		return "admin";
	}
	
	@RequestMapping(value="/user")
	public String user(ModelMap model){
		model.addAttribute("list", productService.list());			
		return "user";
	}
	
	private String getRole() {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next().getAuthority();
	}	
}
