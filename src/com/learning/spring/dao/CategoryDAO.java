package com.learning.spring.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.learning.spring.dto.CategoryDTO;
import com.learning.spring.models.Category;

public interface CategoryDAO {
	
	public boolean checkIfCategoryExists(String user_id , Category category) throws SQLException;

	public boolean addCategory(String user_id, Category category) throws SQLException;

	public ArrayList<CategoryDTO> getCategories(String user_id) throws SQLException;

	boolean deleteCategory(String categoryId) throws SQLException;
	
}
