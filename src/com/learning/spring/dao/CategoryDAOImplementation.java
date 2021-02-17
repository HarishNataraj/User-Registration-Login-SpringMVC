package com.learning.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.learning.spring.dto.CategoryDTO;

public class CategoryDAOImplementation implements CategoryDAO{

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean checkIfCategoryExists(int user_id, String categoryName) {
		String query = "Select * from categories where user_id=? AND title=?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, user_id);
			preparedStatement.setString(2, categoryName);
			ResultSet result = preparedStatement.executeQuery();
			if(result.next()) {
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
	public boolean addCategory(int user_id, String categoryName) {
		String query = "insert into categories (user_id,title) values(?,?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, user_id);
			preparedStatement.setString(2, categoryName);
			int result = preparedStatement.executeUpdate();
			if(result == 1) {
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
	public ArrayList<CategoryDTO> getCategories(int user_id) {
		String query = "Select * from categories where user_id=?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<CategoryDTO> categoryDTOList = new ArrayList<>();
		CategoryDTO categoryDTO = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, user_id);
			ResultSet result = preparedStatement.executeQuery();
			while(result.next()) {
				categoryDTO = new CategoryDTO();
				categoryDTO.setCategory_id(result.getInt("category_id"));
				categoryDTO.setCategory_name(result.getString("title"));
				categoryDTOList.add(categoryDTO);
			}
			return categoryDTOList;
		} catch (SQLException e) {
			e.printStackTrace();
			return categoryDTOList;
		}
	}

	@Override
	public boolean deleteCategory(int categoryId) {
		String query = "delete from categories where category_id=?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, categoryId);
			int result = preparedStatement.executeUpdate();
			if(result > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
}
