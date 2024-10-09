package com.wg.banking.service;

import com.wg.banking.model.Account;
import com.wg.banking.model.User;

public interface AccountService {

	public Account createAccount(User user);
	
	public Account deposit(String accountNumber, double amount);

	public Account withdraw(String accountNumber, double amount);

	public Account transfer(String sourceAccountNumber, String targetAccountNumber, double amount);
}