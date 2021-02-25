package com.learning.spring.services;

import java.sql.SQLException;
import java.util.ArrayList;
import com.learning.spring.dao.TransactionDAO;
import com.learning.spring.dto.TransactionDTO;
import com.learning.spring.models.Transaction;

public class TransactionServiceImplementation implements TransactionService{

	private TransactionDAO transactiondao;

	public void setTransactiondao(TransactionDAO transactiondao) {
		this.transactiondao = transactiondao;
	}

	@Override
	public boolean addTransaction(Transaction transaction) throws SQLException {
		
		String transactionId = transaction.getTransactionId();
		String userId = transaction.getUserId();
		String categoryId = transaction.getCategoryId();
		double transactionAmount = transaction.getTransactionAmount();
		String transactionMode = transaction.getTransactionMode();
		String transactionDate = transaction.getTransactionDate();
		
		return transactiondao.addTransaction(transactionId, categoryId, userId, transactionAmount, transactionMode, transactionDate );
		
	}

	@Override
	public ArrayList<TransactionDTO> getTransactions(String categoryId, String userId) throws SQLException {
		return transactiondao.getTransactions(categoryId,userId);
		
	}

	@Override
	public double calculateExpense(String categoryId, String userId) throws SQLException {
		double expense = 0;
		
		ArrayList<TransactionDTO> transactionDTOList = transactiondao.getTransactions(categoryId, userId);
		for(TransactionDTO transactionDto : transactionDTOList) {
			expense += transactionDto.getTransactionAmount();
		}
		return expense;
		
	}

	@Override
	public boolean deleteTransaction(String transactionId) throws SQLException {
		return transactiondao.deleteTransaction(transactionId);
		
	}
	
	
}
