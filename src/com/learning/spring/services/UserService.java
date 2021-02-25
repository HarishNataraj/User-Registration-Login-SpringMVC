package com.learning.spring.services;

import java.sql.SQLException;

import com.learning.spring.models.User;

public interface UserService {
	
	public boolean saveUser(User user) throws SQLException;
	public int authenticateUser(String email, String password) throws SQLException;
	
}
