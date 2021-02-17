package com.learning.spring.services;

import java.util.ArrayList;

import com.learning.spring.dao.CategoryDAO;
import com.learning.spring.dto.CategoryDTO;

public class CategoryServiceImplementation implements CategoryService{
	
	private CategoryDAO categoryDao;
	
	public void setCategoryDao(CategoryDAO categoryDao) {
		this.categoryDao = categoryDao;
	}



	@Override
	public boolean addCategory(int user_id, String categoryName) {
		
		if(!categoryDao.checkIfCategoryExists(user_id, categoryName)) {
			if(categoryDao.addCategory(user_id, categoryName)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
		
	}

	@Override
	public ArrayList<CategoryDTO> getAllCategories(int user_id) {	
		return categoryDao.getCategories(user_id);
	}



	@Override
	public boolean deleteCategory(int categoryId) {
		return categoryDao.deleteCategory(categoryId);
		
	}

}
