package com.learning.spring.services;

import java.sql.SQLException;
import java.util.ArrayList;
import com.learning.spring.dto.TransactionDTO;
import com.learning.spring.models.Transaction;

public interface TransactionService {

	boolean addTransaction(Transaction transaction) throws SQLException;

	ArrayList<TransactionDTO> getTransactions(int categoryId, int userId) throws SQLException;

	double calculateExpense(int categoryId, int attribute) throws SQLException;

	boolean deleteTransaction(int transactionId) throws SQLException;

}
