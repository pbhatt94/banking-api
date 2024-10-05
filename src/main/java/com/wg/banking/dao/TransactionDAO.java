package com.wg.banking.dao;

import java.sql.SQLException;
import java.util.List;

import com.wg.banking.model.Transaction;

public interface TransactionDAO {
	public Transaction getTransactionById(String transactionId) throws ClassNotFoundException, SQLException;

	public List<Transaction> getAllTransactions() throws ClassNotFoundException, SQLException;

	public List<Transaction> getAllTransactions(String accountNo) throws ClassNotFoundException, SQLException;

	public boolean addTransaction(Transaction transaction) throws ClassNotFoundException, SQLException;
}
