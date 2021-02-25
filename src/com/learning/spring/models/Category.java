package com.learning.spring.models;

import javax.validation.constraints.NotBlank;

public class Category {
	
	private String category_id;
	@NotBlank(message = "Required")
	private String categoryName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	
	
}
