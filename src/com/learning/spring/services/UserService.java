package com.learning.spring.services;

import com.learning.spring.models.User;

public interface UserService {
	
	public boolean saveUser(User user);
	public int authenticateUser(String email, String password);
	
}
