package com.wg.banking.service;

import java.util.List;

import com.wg.banking.dto.TransactionDTO;
import com.wg.banking.model.Account;
import com.wg.banking.model.User;

public interface AccountService {

	public Account createAccount(User user);
	
	public Account getAccountById(String accountId);
	
	public List<Account> getAllAccounts();
	
	public Account deposit(String accountId, TransactionDTO transaction, User user);

	public Account withdraw(String accountId, TransactionDTO transaction, User user);

	public Account transfer(String accountId, TransactionDTO transaction, User user);
}