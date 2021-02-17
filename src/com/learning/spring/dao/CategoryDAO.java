package com.learning.spring.dao;

import java.util.ArrayList;

import com.learning.spring.dto.CategoryDTO;

public interface CategoryDAO {
	
	public boolean checkIfCategoryExists(int user_id , String categoryName);

	public boolean addCategory(int user_id, String categoryName);

	public ArrayList<CategoryDTO> getCategories(int user_id);

	boolean deleteCategory(int categoryId);
	
}
