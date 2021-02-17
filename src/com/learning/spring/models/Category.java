package com.learning.spring.models;

import javax.validation.constraints.NotBlank;

public class Category {
	
	@NotBlank(message = "Required")
	private String categoryName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
