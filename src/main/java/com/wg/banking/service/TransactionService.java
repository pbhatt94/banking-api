package com.wg.banking.service;

import java.util.List;

import com.wg.banking.model.Transaction;

public interface TransactionService {
	public List<Transaction> getAllTransactionsByUserId(String userId, Integer pageNumber, Integer pageSize);
}