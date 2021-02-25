package com.learning.spring.services;

import java.sql.SQLException;
import java.util.ArrayList;

import com.learning.spring.dto.CategoryDTO;

public interface CategoryService {
	
	public boolean addCategory(int user_id, String categoryName) throws SQLException;
	public ArrayList<CategoryDTO> getAllCategories(int user_id) throws SQLException;
	public boolean deleteCategory(int categoryId) throws SQLException;
}
