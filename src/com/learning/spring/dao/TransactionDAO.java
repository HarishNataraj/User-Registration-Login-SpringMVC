package com.learning.spring.dao;

import java.util.ArrayList;
import com.learning.spring.dto.TransactionDTO;

public interface TransactionDAO {

	boolean addTransaction(int categoryId, int userId, double transactionAmount, String transactionMode,
			String transactionDate);

	ArrayList<TransactionDTO> getTransactions(int categoryId, int userId);

	boolean deleteTransaction(int transactionId);

//	void checkIfCategoryIdExists(int categoryId);

}
