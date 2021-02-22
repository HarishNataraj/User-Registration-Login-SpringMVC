package com.learning.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.learning.spring.dto.TransactionDTO;

public class TransactionDAOImplementation implements TransactionDAO{

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean addTransaction(int categoryId, int userId, double transactionAmount, String transactionMode,
			String transactionDate) {

		String query = "insert into transactions (category_id,user_id,amount,transaction_mode,transaction_date) values(?,?,?,?,?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, categoryId);
			preparedStatement.setInt(2, userId);
			preparedStatement.setDouble(3, transactionAmount);
			preparedStatement.setString(4, transactionMode);
			preparedStatement.setString(5, transactionDate);
			int result = preparedStatement.executeUpdate();
			if(result == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		
	}

	@Override
	public ArrayList<TransactionDTO> getTransactions(int categoryId, int userId) {
		String query = "select * from transactions where category_id=? AND user_id=?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, categoryId);
			preparedStatement.setInt(2, userId);
			ResultSet result = preparedStatement.executeQuery();
			ArrayList<TransactionDTO> transactionDTOList = new ArrayList<>();
			TransactionDTO transactionDTO;
			while(result.next()) {
				transactionDTO = new TransactionDTO();
				transactionDTO.setTransactionId(result.getInt("transaction_id"));
				transactionDTO.setTransactionAmount(result.getDouble("amount"));
				transactionDTO.setTransactionDate(result.getString("transaction_date"));
				transactionDTO.setTransactionMode(result.getString("transaction_mode"));
				transactionDTOList.add(transactionDTO);
			}
			return transactionDTOList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public boolean deleteTransaction(int transactionId) {
		String query = "delete from transactions where transaction_id=?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, transactionId);
			int result = preparedStatement.executeUpdate();
			if(result > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
