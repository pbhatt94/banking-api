package com.wg.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wg.banking.model.Transaction;
import com.wg.banking.service.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TransactionController {

	@Autowired
	private final TransactionService transactionService;

	@GetMapping("/api/transactions")
	public List<Transaction> getAllTransactions() {
		List<Transaction> transactions = transactionService.getAllTransactionsByUserId(null);
		return transactions;
	} 
}
