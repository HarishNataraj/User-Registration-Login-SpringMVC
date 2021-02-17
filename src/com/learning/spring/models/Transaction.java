package com.learning.spring.models;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;


public class Transaction {
	
	private int categoryId;
	private int userId;
	
	@DecimalMin("0.1")
	private double transactionAmount;
	
	@NotBlank(message = "Required")
	private String transactionMode;
	
	@NotBlank(message = "Required")
	private String transactionDate;
	
	public double getTransactionAmount() {
		return transactionAmount;
	}
	
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	
	public String getTransactionMode() {
		return transactionMode;
	}
	
	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}
	
	public String getTransactionDate() {
		return transactionDate;
	}
	
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
	
}
