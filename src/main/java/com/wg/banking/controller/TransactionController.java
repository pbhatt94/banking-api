package com.wg.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wg.banking.constants.ApiMessages;
import com.wg.banking.dto.ApiResponseHandler;
import com.wg.banking.model.ApiResponseStatus;
import com.wg.banking.model.Transaction;
import com.wg.banking.service.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TransactionController {

	@Autowired
	private final TransactionService transactionService;

	@GetMapping("/account/{accountId}/transactions")
	public ResponseEntity<Object> getAllTransactionsByUserId(@PathVariable String accountId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "100", required = false) Integer pageSize,
			@RequestParam(value = "transactionType", required = false) String transactionType,
	        @RequestParam(value = "minAmount", required = false) Double minAmount,
	        @RequestParam(value = "maxAmount", required = false) Double maxAmount) {
		List<Transaction> transactions = transactionService.getAllTransactionsByUserId(accountId, pageNumber, pageSize, transactionType, minAmount, maxAmount);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.OK,
				ApiMessages.TRANSACTIONS_FETCHED_SUCCESSFULLY_MESSAGE, transactions);
	}

	@GetMapping("/transactions") 
	public ResponseEntity<Object> getAllTransactions(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(value = "transactionType", required = false) String transactionType,
	        @RequestParam(value = "minAmount", required = false) Double minAmount,
	        @RequestParam(value = "maxAmount", required = false) Double maxAmount) {
		List<Transaction> transactions = transactionService.getAllTransactions(pageNumber, pageSize, transactionType, minAmount, maxAmount);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.OK,
				ApiMessages.TRANSACTIONS_FETCHED_SUCCESSFULLY_MESSAGE, transactions);
	}
}