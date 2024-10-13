package com.wg.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wg.banking.constants.ApiMessages;
import com.wg.banking.dto.ApiResponseHandler;
import com.wg.banking.dto.TransactionDTO;
import com.wg.banking.exception.CustomerNotFoundException;
import com.wg.banking.exception.MissingTransactionDetailsException;
import com.wg.banking.model.Account;
import com.wg.banking.model.ApiResponseStatus;
import com.wg.banking.model.User;
import com.wg.banking.service.AccountService;
import com.wg.banking.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AccountController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private UserService userService;

	public AccountController(AccountService accountService, UserService userService) {
		this.accountService = accountService;
		this.userService = userService;
	}

	@PostMapping("/account/{accountId}/deposit")
	public ResponseEntity<Object> deposit(@PathVariable String accountId, @Valid @RequestBody TransactionDTO transaction) {
		User user = userService.getCurrentUser();
		if (user.getAccount() == null) {
			throw new CustomerNotFoundException(ApiMessages.NOT_A_CUSTOMER_ERROR);
		}
		if (transaction == null) {
			return ResponseEntity.badRequest().build();
		}
		Account account = accountService.deposit(accountId, transaction, user);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.CREATED,
				ApiMessages.DEPOSIT_SUCCESSFUL_MESSAGE, account);
	}

	@PostMapping("account/{accountId}/withdraw")
	public ResponseEntity<Object> withdraw(@PathVariable String accountId, @RequestBody TransactionDTO transaction) {
		User user = userService.getCurrentUser();
		if (user.getAccount() == null) {
			throw new CustomerNotFoundException(ApiMessages.NOT_A_CUSTOMER_ERROR);
		}
		if (transaction == null) {
			return ResponseEntity.badRequest().build();
		}
		Account account = accountService.withdraw(accountId, transaction, user);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.CREATED,
				ApiMessages.WITHDRAWAL_SUCCESSFUL_MESSAGE, account);
	}

	@PostMapping("account/{accountId}/transfer")
	public ResponseEntity<Object> transfer(@PathVariable String accountId,
			@Valid @RequestBody TransactionDTO transaction) {
		User user = userService.getCurrentUser();
		if (user.getAccount() == null) {
			throw new CustomerNotFoundException(ApiMessages.NOT_A_CUSTOMER_ERROR);
		}
		if (!isValidTransferObject(transaction)) {
			throw new MissingTransactionDetailsException(ApiMessages.BAD_REQUEST_MESSAGE);
		}
		Account account = accountService.transfer(accountId, transaction, user);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.CREATED,
				ApiMessages.TRANSFER_SUCCESSFUL_MESSAGE, account);
	}

	@GetMapping("account/{accountId}")
	public ResponseEntity<Object> getAccountById(@PathVariable String accountId) {
		Account account = accountService.getAccountById(accountId);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.OK,
				ApiMessages.ACCOUNT_FETCHED_SUCCESSFULLY_MESSAGE, account);
	}

	@GetMapping("/accounts")
	public ResponseEntity<Object> getAllAccounts() {
		List<Account> accounts = accountService.getAllAccounts();
		return ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.OK,
				ApiMessages.ACCOUNTS_FETCHED_SUCCESSFULLY_MESSAGE, accounts);
	}

	private boolean isValidTransferObject(TransactionDTO transaction) {
		return !(transaction == null || transaction.getTargetAccountNumber() == null
				|| transaction.getTargetAccountNumber().isEmpty());
	}
}