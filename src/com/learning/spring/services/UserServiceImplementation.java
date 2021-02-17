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
	public int authenticateUser(String email, String password) {
		if (!userDao.findUserByEmail(email)) {
			int user_id = userDao.authenticateUser(email, password);
			if (user_id > 0) {
				return user_id;
			} else {
				return -1;
			}
		} else {
			return -1;
		}
	}

}
