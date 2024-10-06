package com.wg.banking.service;

import com.wg.banking.model.Account;
import com.wg.banking.model.User;

public interface AccountService {

	public Account createAccount(User user);
	
	public void deposit(String accountNumber, double amount);
}