package com.learning.spring.dto;

public class TransactionDTO {

	private int transactionId;
	private double transactionAmount;
	private String transactionMode;
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

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	
	
	
	
	
}
