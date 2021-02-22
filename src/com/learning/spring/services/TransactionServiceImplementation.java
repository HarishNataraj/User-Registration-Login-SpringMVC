package com.learning.spring.services;

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
	public boolean addTransaction(Transaction transaction) {
		
		int userId = transaction.getUserId();
		int categoryId = transaction.getCategoryId();
		double transactionAmount = transaction.getTransactionAmount();
		String transactionMode = transaction.getTransactionMode();
		String transactionDate = transaction.getTransactionDate();
		
		return transactiondao.addTransaction(categoryId, userId, transactionAmount, transactionMode, transactionDate );
		
	}

	@Override
	public ArrayList<TransactionDTO> getTransactions(int categoryId, int userId) {
		return transactiondao.getTransactions(categoryId,userId);
		
	}

	@Override
	public double calculateExpense(int categoryId, int userId) {
		double expense = 0;
		
		ArrayList<TransactionDTO> transactionDTOList = transactiondao.getTransactions(categoryId, userId);
		for(TransactionDTO transactionDto : transactionDTOList) {
			expense += transactionDto.getTransactionAmount();
		}
		return expense;
		
	}

	@Override
	public boolean deleteTransaction(int transactionId) {
		return transactiondao.deleteTransaction(transactionId);
		
	}
	
	
}
