package com.wg.banking.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.wg.banking.service.AccountService;
import com.wg.banking.service.TransactionService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {

	private final AccountService accountService;
	private final TransactionService transactionService;
	
//	@PostMapping("/deposit")
//	public 
}