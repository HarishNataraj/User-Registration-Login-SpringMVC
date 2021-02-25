package com.learning.spring.services;

import java.sql.SQLException;
import java.util.ArrayList;

import com.learning.spring.dao.CategoryDAO;
import com.learning.spring.dto.CategoryDTO;
import com.learning.spring.models.Category;

public class CategoryServiceImplementation implements CategoryService{
	
	private CategoryDAO categoryDao;
	
	public void setCategoryDao(CategoryDAO categoryDao) {
		this.categoryDao = categoryDao;
	}



	@Override
	public boolean addCategory(String user_id, Category category ) throws SQLException {
		
		if(!categoryDao.checkIfCategoryExists(user_id, category)) {
			if(categoryDao.addCategory(user_id, category)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
		
	}

	@Override
	public ArrayList<CategoryDTO> getAllCategories(String user_id) throws SQLException {	
		return categoryDao.getCategories(user_id);
	}



	@Override
	public boolean deleteCategory(String categoryId) throws SQLException {
		return categoryDao.deleteCategory(categoryId);
		
	}

}
