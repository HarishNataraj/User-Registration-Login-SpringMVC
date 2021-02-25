package com.learning.spring.services;

import java.sql.SQLException;
import java.util.ArrayList;
import com.learning.spring.dto.CategoryDTO;
import com.learning.spring.models.Category;

public interface CategoryService {
	
	public boolean addCategory(String user_id, Category category) throws SQLException;
	public ArrayList<CategoryDTO> getAllCategories(String user_id) throws SQLException;
	public boolean deleteCategory(String categoryId) throws SQLException;
}
