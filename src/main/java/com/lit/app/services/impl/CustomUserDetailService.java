package com.lit.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lit.app.entities.User;
import com.lit.app.repo.UserDao;

@Service
public class CustomUserDetailService  implements UserDetailsService{

	@Autowired
	UserDao userDaoImpl;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userDaoImpl.findUserByUserName(username);
		if (user == null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("User not found");
		}
		return user;
	}

}
