package com.learning.spring.dao;


import java.sql.SQLException;

import com.learning.spring.models.User;

public interface UserDAO {
	
	public boolean saveUser(User user) throws SQLException;
	public boolean findUserByEmail(String email) throws SQLException;
	public int authenticateUser(String email, String password) throws SQLException;
	
}
