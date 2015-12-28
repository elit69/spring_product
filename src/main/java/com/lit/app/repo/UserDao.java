package com.lit.app.repo;

import com.lit.app.entities.User;

public interface UserDao {
	
	public User findUserByUserName(String username);
	public User loadUserById(int id);	

}
