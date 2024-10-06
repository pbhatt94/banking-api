package com.wg.banking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.wg.banking.model.Transaction;
import com.wg.banking.repository.TransactionRepository;
import com.wg.banking.service.TransactionService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
	private final TransactionRepository transactionRepository;
	
	@Override
	public List<Transaction> getAllTransactionsByAccountNumber(String accountNumber) {
		List<Transaction> transactions = transactionRepository.findBySourceAccount_AccountNumberOrTargetAccount_AccountNumber(accountNumber, accountNumber);
		transactions = transactions.stream().sorted((t1, t2) -> t2.getCreatedAt().compareTo(t1.getCreatedAt()))
                .collect(Collectors.toList());
		return transactions;
	}
}