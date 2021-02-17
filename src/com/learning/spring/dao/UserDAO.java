package com.learning.spring.dao;


import com.learning.spring.models.User;

public interface UserDAO {
	
	public boolean saveUser(User user);
	public boolean findUserByEmail(String email);
	public int authenticateUser(String email, String password);
	
}
