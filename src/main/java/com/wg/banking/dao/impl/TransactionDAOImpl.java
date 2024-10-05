package com.wg.banking.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.wg.banking.constants.TransactionConstants;
import com.wg.banking.dao.GenericDAO;
import com.wg.banking.dao.TransactionDAO;
import com.wg.banking.model.Transaction;

public class TransactionDAOImpl extends GenericDAO<Transaction> implements TransactionDAO {
	
	public TransactionDAOImpl() {
		super();
	}

	@Override
	protected Transaction mapResultSetToEntity(ResultSet resultSet) throws SQLException {
		Transaction transaction = new Transaction();
		
		transaction.setTransactionId(resultSet.getString(TransactionConstants.TRANSACTION_ID_COLUMN));
		transaction.setAmount(resultSet.getDouble(TransactionConstants.AMOUNT_COLUMN));
		String transactionTypeString = resultSet.getString(TransactionConstants.TRANSACTION_TYPE_COLUMN);
		transaction.setTransactionType(Transaction.TransactionType.valueOf(transactionTypeString));
		transaction.setSourceAccountId(resultSet.getString(TransactionConstants.SOURCE_ACCOUNT_ID_COLUMN));
		transaction.setDestinationAccountId(resultSet.getString(TransactionConstants.DESTINATION_ACCOUNT_ID_COLUMN));
		transaction.setCreatedAt(resultSet.getTimestamp(TransactionConstants.CREATED_AT_COLUMN));
		
		return transaction;
	}

	public Transaction getTransactionById(String transactionId) throws ClassNotFoundException, SQLException {
		String query = String.format("SELECT * FROM %s WHERE %s = '%s'", 
				TransactionConstants.TRANSACTION_TABLE_NAME,
				TransactionConstants.TRANSACTION_ID_COLUMN,
				transactionId
				);
		return get(query);
	}

	public List<Transaction> getAllTransactions() throws ClassNotFoundException, SQLException {
		String query = String.format("SELECT * FROM %s", TransactionConstants.TRANSACTION_TABLE_NAME);
		return getAll(query);
	}
	
	public List<Transaction> getAllTransactions(String accountNo) throws ClassNotFoundException, SQLException {
		String query = "SELECT * FROM Transaction WHERE source_account_id = \'" + accountNo + "\' or destination_account_id = \'" + accountNo + "\'";
		List<Transaction> transactions = getAll(query);
		return transactions;
    }

	public boolean addTransaction(Transaction transaction) throws ClassNotFoundException, SQLException {
		String query;
		if(transaction.getSourceAccountId() == null) {
			query = String.format(
			        "INSERT INTO %s (%s, %s, %s, %s, %s) VALUES ('%s', %.2f, '%s', NULL, '%s')",
			        TransactionConstants.TRANSACTION_TABLE_NAME,
			        TransactionConstants.TRANSACTION_ID_COLUMN,
			        TransactionConstants.AMOUNT_COLUMN,
			        TransactionConstants.TRANSACTION_TYPE_COLUMN,
			        TransactionConstants.SOURCE_ACCOUNT_ID_COLUMN,
			        TransactionConstants.DESTINATION_ACCOUNT_ID_COLUMN,
			        transaction.getTransactionId(),
			        transaction.getAmount(),
			        transaction.getTransactionType(),
			        transaction.getDestinationAccountId()
			    ); 
		} else if(transaction.getDestinationAccountId() == null) {
			query = String.format(
			        "INSERT INTO %s (%s, %s, %s, %s, %s) VALUES ('%s', %.2f, '%s', '%s', NULL)",
			        TransactionConstants.TRANSACTION_TABLE_NAME,
			        TransactionConstants.TRANSACTION_ID_COLUMN,
			        TransactionConstants.AMOUNT_COLUMN,
			        TransactionConstants.TRANSACTION_TYPE_COLUMN,
			        TransactionConstants.SOURCE_ACCOUNT_ID_COLUMN,
			        TransactionConstants.DESTINATION_ACCOUNT_ID_COLUMN,
			        transaction.getTransactionId(),
			        transaction.getAmount(),
			        transaction.getTransactionType(),
			        transaction.getSourceAccountId()
			    ); 
		} else {
			query = String.format(
			        "INSERT INTO %s (%s, %s, %s, %s, %s) VALUES ('%s', %.2f, '%s', '%s', '%s')",
			        TransactionConstants.TRANSACTION_TABLE_NAME,
			        TransactionConstants.TRANSACTION_ID_COLUMN,
			        TransactionConstants.AMOUNT_COLUMN,
			        TransactionConstants.TRANSACTION_TYPE_COLUMN,
			        TransactionConstants.SOURCE_ACCOUNT_ID_COLUMN,
			        TransactionConstants.DESTINATION_ACCOUNT_ID_COLUMN,
			        transaction.getTransactionId(),
			        transaction.getAmount(),
			        transaction.getTransactionType(),
			        transaction.getSourceAccountId(),
			        transaction.getDestinationAccountId()
			    ); 
		}
		return update(query);		
	}
}

