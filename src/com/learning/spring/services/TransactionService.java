package com.learning.spring.services;

import java.util.ArrayList;
import com.learning.spring.dto.TransactionDTO;
import com.learning.spring.models.Transaction;

public interface TransactionService {

	boolean addTransaction(Transaction transaction);

	ArrayList<TransactionDTO> getTransactions(int categoryId, int userId);

	double calculateExpense(int categoryId, int attribute);

	boolean deleteTransaction(int transactionId);

}
