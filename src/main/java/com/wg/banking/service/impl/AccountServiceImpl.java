package com.wg.banking.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wg.banking.constants.ApiMessages;
import com.wg.banking.exception.AccountNotFoundException;
import com.wg.banking.exception.InvalidAmountException;
import com.wg.banking.exception.SourceSameAsTargetException;
import com.wg.banking.helper.AccountNumberGenerator;
import com.wg.banking.exception.InsufficientBalanceException;
import com.wg.banking.model.Account;
import com.wg.banking.model.Transaction;
import com.wg.banking.model.TransactionType;
import com.wg.banking.model.User;
import com.wg.banking.repository.AccountRepository;
import com.wg.banking.repository.TransactionRepository;
import com.wg.banking.service.AccountService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
	private final AccountRepository accountRepository;
	private final TransactionRepository transactionRepository;

	@Override
	public Account createAccount(User user) {
		Account account = new Account();
		account.setAccountNumber(AccountNumberGenerator.generateAccountNumber());
		account.setBalance(0.0);
		account.setUser(user);
		return accountRepository.save(account);
	}

	@Override
	public Account deposit(String accountNumber, double amount) {
		validateAmount(amount);

		Optional<Account> existingAccount = accountRepository.findByAccountNumber(accountNumber);
		if (!existingAccount.isPresent()) {
			throw new AccountNotFoundException(ApiMessages.INVALID_ACCOUNT_NUMBER + ": " + accountNumber);
		}

		Account account = existingAccount.get();
		double currentBalance = account.getBalance();
		double newBalance = currentBalance + amount;
		account.setBalance(newBalance);
		Account savedAccount = accountRepository.save(account);

		Transaction transaction = new Transaction();
		transaction.setTransactionType(TransactionType.DEPOSIT);
		transaction.setAmount(amount);
		transaction.setSourceAccount(account);
		transactionRepository.save(transaction);

		return savedAccount;
	}

	@Override
	public Account withdraw(String accountNumber, double amount) {
		validateAmount(amount);
		Optional<Account> existingAccount = accountRepository.findByAccountNumber(accountNumber);
		if (!existingAccount.isPresent()) {
			throw new AccountNotFoundException(ApiMessages.INVALID_ACCOUNT_NUMBER + ": " + accountNumber);
		}

		Account account = existingAccount.get();
		double currentBalance = account.getBalance();
		validateFunds(currentBalance, amount);
		double newBalance = currentBalance - amount;
		account.setBalance(newBalance);
		Account savedAccount = accountRepository.save(account);

		Transaction transaction = new Transaction();
		transaction.setTransactionType(TransactionType.WITHDRAWAL);
		transaction.setAmount(amount);
		transaction.setSourceAccount(account);
		transactionRepository.save(transaction);

		return savedAccount;
	}

	@Override
	public Account transfer(String sourceAccountNumber, String targetAccountNumber, double amount) {
		validateAmount(amount);
		Optional<Account> existingSourceAccount = accountRepository.findByAccountNumber(sourceAccountNumber);
		Optional<Account> existingTargetAccount = accountRepository.findByAccountNumber(targetAccountNumber);
		if (isAccountNull(existingSourceAccount, existingTargetAccount)) {
			throw new AccountNotFoundException(ApiMessages.INVALID_ACCOUNT_NUMBER);
		}
		if (isSourceSameAsTarget(sourceAccountNumber, targetAccountNumber)) {
			throw new SourceSameAsTargetException(ApiMessages.SOURCE_CANNOT_BE_SAME_AS_TARGET_ERROR);
		}

		Account sourceAccount = existingSourceAccount.get();
		Account targetAccount = existingTargetAccount.get();
		double currentSourceAccountBalance = sourceAccount.getBalance();
		validateFunds(currentSourceAccountBalance, amount);
		double newSourceAccountBalance = currentSourceAccountBalance - amount;
		sourceAccount.setBalance(newSourceAccountBalance);
		Account savedAccount = accountRepository.save(sourceAccount);

		double currentTargetAccountBalance = targetAccount.getBalance();
		double newTargetAccountBalance = currentTargetAccountBalance + amount;
		targetAccount.setBalance(newTargetAccountBalance);
		accountRepository.save(targetAccount);

		Transaction transaction = new Transaction();
		transaction.setTransactionType(TransactionType.TRANSFER);
		transaction.setAmount(amount);
		transaction.setSourceAccount(sourceAccount);
		transactionRepository.save(transaction);

		return savedAccount;
	}

	private boolean isSourceSameAsTarget(String sourceAccountNumber, String targetAccountNumber) {
		return sourceAccountNumber.equals(targetAccountNumber);
	}

	private boolean isAccountNull(Optional<Account> existingAccount, Optional<Account> existingTargetAccount) {
		return !existingAccount.isPresent() || !existingTargetAccount.isPresent();
	}

	private void validateAmount(double amount) {
		if (amount <= 0) {
			throw new InvalidAmountException(ApiMessages.AMOUNT_NEGATIVE_ERROR);
		}
	}

	private void validateFunds(double sourceAccountBalance, double amount) {
		if (sourceAccountBalance < amount) {
			throw new InsufficientBalanceException(ApiMessages.INSUFFICIENT_BALANCE_ERROR);
		}
	}

}