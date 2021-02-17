package com.learning.spring.services;

import java.util.ArrayList;

import com.learning.spring.dto.CategoryDTO;

public interface CategoryService {
	
	public boolean addCategory(int user_id, String categoryName);
	public ArrayList<CategoryDTO> getAllCategories(int user_id);
	public boolean deleteCategory(int categoryId);
}
