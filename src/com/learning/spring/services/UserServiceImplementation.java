package com.learning.spring.services;

import com.learning.spring.dao.UserDAO;
import com.learning.spring.models.User;

public class UserServiceImplementation implements UserService{
	
	private UserDAO userDao;

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean saveUser(User user) {
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
	public boolean authenticateUser(String email, String password) {
		if (!userDao.findUserByEmail(email)) {
			if (userDao.authenticateUser(email, password)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
