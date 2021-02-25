package com.learning.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.learning.spring.dto.CategoryDTO;
import com.learning.spring.models.Category;

public class CategoryDAOImplementation implements CategoryDAO{

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean checkIfCategoryExists(String user_id, Category category) throws SQLException {
		String query = "Select * from categories where user_id=? AND title=?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user_id);
			preparedStatement.setString(2, category.getCategoryName());
			ResultSet result = preparedStatement.executeQuery();
			if(result.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			throw(e);
		}
	}

	@Override
	public boolean addCategory(String user_id, Category category) throws SQLException {
		String query = "insert into categories (category_id,user_id,title) values(?,?,?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, category.getCategory_id());
			preparedStatement.setString(2, user_id);
			preparedStatement.setString(3, category.getCategoryName());
			int result = preparedStatement.executeUpdate();
			if(result == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			throw(e);
		}
		
	}

	@Override
	public ArrayList<CategoryDTO> getCategories(String user_id) throws SQLException {
		String query = "Select * from categories where user_id=?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<CategoryDTO> categoryDTOList = new ArrayList<>();
		CategoryDTO categoryDTO = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user_id);
			ResultSet result = preparedStatement.executeQuery();
			while(result.next()) {
				categoryDTO = new CategoryDTO();
				categoryDTO.setCategory_id(result.getString("category_id"));
				categoryDTO.setCategory_name(result.getString("title"));
				categoryDTOList.add(categoryDTO);
			}
			return categoryDTOList;
		} catch (SQLException e) {
			throw(e);
		}
	}

	@Override
	public boolean deleteCategory(String categoryId) throws SQLException {
		String query = "delete from categories where category_id=?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, categoryId);
			int result = preparedStatement.executeUpdate();
			if(result > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			throw(e);
		}
		
	}
	
}
