package com.learning.spring.services;

import java.sql.SQLException;
import java.util.ArrayList;
import com.learning.spring.dto.TransactionDTO;
import com.learning.spring.models.Transaction;

public interface TransactionService {

	boolean addTransaction(Transaction transaction) throws SQLException;

	ArrayList<TransactionDTO> getTransactions(String categoryId, String userId) throws SQLException;

	double calculateExpense(String categoryId, String attribute) throws SQLException;

	boolean deleteTransaction(String transactionId) throws SQLException;

}
