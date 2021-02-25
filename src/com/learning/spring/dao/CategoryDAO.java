package com.learning.spring.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.learning.spring.dto.CategoryDTO;

public interface CategoryDAO {
	
	public boolean checkIfCategoryExists(int user_id , String categoryName) throws SQLException;

	public boolean addCategory(int user_id, String categoryName) throws SQLException;

	public ArrayList<CategoryDTO> getCategories(int user_id) throws SQLException;

	boolean deleteCategory(int categoryId) throws SQLException;
	
}
