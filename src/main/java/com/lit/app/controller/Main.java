package com.lit.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lit.app.services.ProductService;
import com.lit.app.services.impl.UserServiceImpl;

@Controller /*
			 * Indicates that an annotated class is a "Controller" of Spring
			 * Framework (a web controller)
			 */

/*
 * is used to map the user’s requests to handler classes or methods. It can be
 * applied on the class level and also on the method level.
 */
public class Main {

	@Autowired 
	ProductService productService;
	
	@Autowired
	UserServiceImpl userDetailsService;
	/*
	 * @Autowired, @Inject, and @Resource tell the application context to inject
	 * the beans.
	 * 
	 * @Autowired and @Inject works very similar but @Resource has bit
	 * different.
	 */
	
	@RequestMapping(value="/")
	public String homePahe(ModelMap model){
		model.addAttribute("auth", SecurityContextHolder.getContext().getAuthentication());
		return "home";
	}	
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String homePahe1(){
		return "login";
	}
	
	@RequestMapping(value="/admin/product", method = RequestMethod.GET)
	public String admin(ModelMap model){
		model.addAttribute("list", productService.list());			
		return "admin";
	}
	
	@RequestMapping(value="/admin/product/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> admindelete(ModelMap model, @PathVariable("id") int id){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", productService.delete(id));
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);		
	}
	
	@RequestMapping(value="/author/product", method = RequestMethod.GET)
	public String user(ModelMap model){
		model.addAttribute("list", productService.list());			
		return "author";
	}
	
	@RequestMapping(value="/user/{id}", method = RequestMethod.GET)
	public String user1(ModelMap model, @PathVariable("id") int id){
		model.addAttribute("user", userDetailsService.loadUserById(id));			
		return "user";
	}	
}
