package com.learning.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.sql.DataSource;

import com.learning.spring.dto.TransactionDTO;

public class TransactionDAOImplementation implements TransactionDAO{

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean addTransaction(String transactionId, String categoryId, String userId, double transactionAmount, String transactionMode,
			String transactionDate) throws SQLException {

		String query = "insert into transactions (transaction_id,category_id,user_id,amount,transaction_mode,transaction_date) values(?,?,?,?,?,?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, transactionId);
			preparedStatement.setString(2, categoryId);
			preparedStatement.setString(3, userId);
			preparedStatement.setDouble(4, transactionAmount);
			preparedStatement.setString(5, transactionMode);
			preparedStatement.setString(6, transactionDate);
			int result = preparedStatement.executeUpdate();
			if(result == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			throw(e);
		}
		
		
	}

	@Override
	public ArrayList<TransactionDTO> getTransactions(String categoryId, String userId) throws SQLException {
		String query = "select * from transactions where category_id=? AND user_id=?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, categoryId);
			preparedStatement.setString(2, userId);
			ResultSet result = preparedStatement.executeQuery();
			ArrayList<TransactionDTO> transactionDTOList = new ArrayList<>();
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			TransactionDTO transactionDTO;
			Calendar calendar;
			while(result.next()) {
				transactionDTO = new TransactionDTO();
				transactionDTO.setTransactionId(result.getString("transaction_id"));
				transactionDTO.setTransactionAmount(result.getDouble("amount"));
				long milliSeconds= Long.parseLong(result.getString("transaction_date"));
				calendar = Calendar.getInstance();
				calendar.setTimeInMillis(milliSeconds);
				transactionDTO.setTransactionDate(formatter.format(calendar.getTime()));
				transactionDTO.setTransactionMode(result.getString("transaction_mode"));
				transactionDTOList.add(transactionDTO);
			}
			return transactionDTOList;
		} catch (SQLException e) {
			throw(e);
		}	
	}

	@Override
	public boolean deleteTransaction(String transactionId) throws SQLException {
		String query = "delete from transactions where transaction_id=?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, transactionId);
			int result = preparedStatement.executeUpdate();
			if(result > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			throw(e);
		}
		
	}
}
