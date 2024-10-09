package com.wg.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.wg.banking.constants.ApiMessages;
import com.wg.banking.dto.ApiResponseHandler;
import com.wg.banking.model.ApiResponseStatus;
import com.wg.banking.model.Transaction;
import com.wg.banking.service.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TransactionController {

	@Autowired
	private final TransactionService transactionService;

	@GetMapping("/api/user/{userId}/transactions")
	public ResponseEntity<Object>getAllTransactions(@PathVariable String userId) {
		List<Transaction> transactions = transactionService.getAllTransactionsByUserId(userId);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.OK, ApiMessages.TRANSACTIONS_FETCHED_SUCCESSFULLY_MESSAGE, transactions);
	} 
}
