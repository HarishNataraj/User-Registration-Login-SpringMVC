package com.learning.spring.services;

import java.sql.SQLException;

import com.learning.spring.dao.UserDAO;
import com.learning.spring.models.User;

public class UserServiceImplementation implements UserService{
	
	private UserDAO userDao;

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean saveUser(User user) throws SQLException {
		if (userDao.findUserByEmail(user.getEmail())) {
			if (userDao.saveUser(user)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}


	@Override
	public String authenticateUser(String email, String password) throws SQLException {
		if (!userDao.findUserByEmail(email)) {
			String user_id = userDao.authenticateUser(email, password);
			if (user_id != null) {
				return user_id;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

}
