package com.learning.spring.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import com.learning.spring.dto.TransactionDTO;

public interface TransactionDAO {

	boolean addTransaction(String transactionId, String categoryId, String userId, double transactionAmount, String transactionMode,
			String transactionDate) throws SQLException;

	ArrayList<TransactionDTO> getTransactions(String categoryId, String userId) throws SQLException;

	boolean deleteTransaction(String transactionId) throws SQLException;

//	void checkIfCategoryIdExists(int categoryId);

}
