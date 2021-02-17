package com.learning.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.learning.spring.models.User;


public class UserDAOImplementation implements UserDAO{
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean saveUser(User user) {
		String query = "insert into users (first_Name,last_Name,email,password) values(?,?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			int result = ps.executeUpdate();
			if(result != 0) {
				return true;
			} else {
				return false;
			}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
	}

	@Override
	public boolean findUserByEmail(String email) {
		String query = "select * from users where email=?";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			ResultSet result = ps.executeQuery();
			if(result.next()) {
				return false;
			} else {
				return true;
			}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
	}

	@Override
	public int authenticateUser(String email, String password) {
		String query = "select * from users where (email=? AND password=?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet result = ps.executeQuery();
			if(result.next()) {
				int user_id = result.getInt("user_id");
				return user_id;
			} else {
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

}
