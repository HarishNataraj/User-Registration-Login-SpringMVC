package com.learning.spring.models;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;


public class Transaction {
	
	private String transactionId;
	private String categoryId;
	private String userId;
	
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

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	
	
	
	
	
}
