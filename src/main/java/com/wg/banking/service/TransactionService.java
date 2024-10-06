package com.wg.banking.service;

import java.util.List;

import com.wg.banking.model.Transaction;

public interface TransactionService {
	List<Transaction> getAllTransactionsByAccountNumber(String accountNumber);
}