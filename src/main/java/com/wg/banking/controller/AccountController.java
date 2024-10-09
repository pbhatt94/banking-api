package com.wg.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/api/account")
public class AccountController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private UserService userService;

	public AccountController(AccountService accountService, UserService userService) {
		this.accountService = accountService;
		this.userService = userService;
	}

	@PostMapping("/deposit")
	public ResponseEntity<Object> deposit(@RequestBody TransactionDTO transaction) {
		User user = userService.getCurrentUser();
		if (user.getAccount() == null) {
			throw new CustomerNotFoundException(ApiMessages.NOT_A_CUSTOMER_ERROR);
		}
		if (transaction == null) {
			return ResponseEntity.badRequest().build();
		}
		Account account = accountService.deposit(user.getAccount().getAccountNumber(), transaction.getAmount());
		return ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.CREATED,
				ApiMessages.DEPOSIT_SUCCESSFUL_MESSAGE, account);
	}

	@PostMapping("/withdraw")
	public ResponseEntity<Object> withdraw(@RequestBody TransactionDTO transaction) {
		User user = userService.getCurrentUser();
		if (user.getAccount() == null) {
			throw new CustomerNotFoundException(ApiMessages.NOT_A_CUSTOMER_ERROR);
		}
		if (transaction == null) {
			return ResponseEntity.badRequest().build();
		}
		Account account = accountService.withdraw(user.getAccount().getAccountNumber(), transaction.getAmount());
		return ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.CREATED,
				ApiMessages.WITHDRAWAL_SUCCESSFUL_MESSAGE, account);
	}

	@PostMapping("/transfer")
	public ResponseEntity<Object> transfer(@RequestBody TransactionDTO transaction) {
		User user = userService.getCurrentUser();
		if (user.getAccount() == null) {
			throw new CustomerNotFoundException(ApiMessages.NOT_A_CUSTOMER_ERROR);
		}
		if (!isValidTransferObject(transaction)) {
			throw new MissingTransactionDetailsException(ApiMessages.BAD_REQUEST_MESSAGE);
		}
		Account account = accountService.transfer(user.getAccount().getAccountNumber(),
				transaction.getTargetAccountNumber(), transaction.getAmount());
		return ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.CREATED,
				ApiMessages.TRANSFER_SUCCESSFUL_MESSAGE, account);
	}

	private boolean isValidTransferObject(TransactionDTO transaction) {
		return !(transaction == null || transaction.getTargetAccountNumber() == null
				|| transaction.getTargetAccountNumber().isEmpty());
	}
}