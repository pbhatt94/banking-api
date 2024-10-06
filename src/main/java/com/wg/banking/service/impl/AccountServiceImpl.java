package com.wg.banking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wg.banking.exception.InvalidAmountException;
import com.wg.banking.helper.AccountNumberGenerator;
import com.wg.banking.model.Account;
import com.wg.banking.model.User;
import com.wg.banking.repository.AccountRepository;
import com.wg.banking.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	AccountRepository accountRepository;

	@Autowired
	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public Account createAccount(User user) {
		Account account = new Account();
		account.setAccountNumber(AccountNumberGenerator.generateAccountNumber());
		account.setBalance(0.0);
		account.setUser(user);
		return accountRepository.save(account);
	}

	@Override
	public void deposit(String accountNumber, double amount) {
		if (amount <= 0) {
            throw new InvalidAmountException("Amount can't be negative");
        }
		
		
	}
}
