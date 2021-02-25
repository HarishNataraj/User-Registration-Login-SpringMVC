package com.learning.spring.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import com.learning.spring.dto.TransactionDTO;

public interface TransactionDAO {

	boolean addTransaction(int categoryId, int userId, double transactionAmount, String transactionMode,
			String transactionDate) throws SQLException;

	ArrayList<TransactionDTO> getTransactions(int categoryId, int userId) throws SQLException;

	boolean deleteTransaction(int transactionId) throws SQLException;

//	void checkIfCategoryIdExists(int categoryId);

}
