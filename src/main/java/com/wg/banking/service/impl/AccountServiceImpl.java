package com.wg.banking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wg.banking.constants.ApiMessages;
import com.wg.banking.dto.TransactionDTO;
import com.wg.banking.exception.AccountNotFoundException;
import com.wg.banking.exception.CustomerNotFoundException;
import com.wg.banking.exception.InActiveAccountException;
import com.wg.banking.exception.InvalidAmountException;
import com.wg.banking.exception.ResourceAccessDeniedException;
import com.wg.banking.exception.SourceSameAsTargetException;
import com.wg.banking.exception.UserNotFoundException;
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
	private final double TRANSACTION_LIMIT = 20000;

	@Override
	public Account createAccount(User user) {
		Account account = new Account();
		account.setAccountNumber(AccountNumberGenerator.generateAccountNumber());
		account.setBalance(0.0);
		account.setUser(user);
		return accountRepository.save(account);
	}

	@Override
	public Account deposit(String accountId, TransactionDTO transactionDto, User user) {
		double amount = transactionDto.getAmount();
		validateAmount(amount);

		validateUser(user);
		Account account = user.getAccount();
		validateAccount(accountId, account);
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
	public Account withdraw(String accountId, TransactionDTO transactionDto, User user) {
		double amount = transactionDto.getAmount();
		validateAmount(amount);

		validateUser(user);
		Account account = user.getAccount();
		validateAccount(accountId, account);
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
	public Account transfer(String accountId, TransactionDTO transactionDto, User user) {
		double amount = transactionDto.getAmount();
		validateAmount(amount);

		validateUser(user);
		Account sourceAccount = user.getAccount();
		validateAccount(accountId, sourceAccount);

		if (isSourceSameAsTarget(sourceAccount.getAccountNumber(), transactionDto.getTargetAccountNumber())) {
			throw new SourceSameAsTargetException(ApiMessages.SOURCE_CANNOT_BE_SAME_AS_TARGET_ERROR);
		}

		Optional<Account> existingTargetAccount = accountRepository
				.findByAccountNumber(transactionDto.getTargetAccountNumber());
		if (existingTargetAccount.isEmpty()) {
			throw new AccountNotFoundException(ApiMessages.INVALID_ACCOUNT_NUMBER);
		}

		Account targetAccount = existingTargetAccount.get();
		checkAccountActivity(targetAccount);

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
		transaction.setTargetAccount(targetAccount);
		transactionRepository.save(transaction);

		return savedAccount;
	}

	@Override
	public Account getAccountById(String accountId) {
		Optional<Account> account = accountRepository.findById(accountId);
		if (account.isEmpty()) {
			throw new AccountNotFoundException(ApiMessages.ACCOUNT_NOT_FOUND_MESSAGE);
		}
		return account.get();
	}

	@Override
	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}

	private boolean isSourceSameAsTarget(String sourceAccountNumber, String targetAccountNumber) {
		return sourceAccountNumber.equals(targetAccountNumber);
	}

	private void validateUser(User user) {
		if (user == null) {
			throw new UserNotFoundException(ApiMessages.USER_NOT_FOUND_ERROR);
		}
		if (user.getAccount() == null) {
			throw new CustomerNotFoundException(ApiMessages.NOT_A_CUSTOMER_ERROR);
		}
	}

	private void validateAccount(String accountId, Account account) {
		checkAccountActivity(account);
		validateAccountOwner(accountId, account);
	}

	private void checkAccountActivity(Account account) {
		if (!account.isActive())
			throw new InActiveAccountException(ApiMessages.INACTIVE_ACCOUNT_ERROR);
	}

	private void validateAccountOwner(String accountId, Account account) {
		if (!account.getId().equals(accountId))
			throw new ResourceAccessDeniedException(ApiMessages.ACESS_DENIED_ERROR);
	}

	private void validateAmount(double amount) {
		if (amount <= 0) {
			throw new InvalidAmountException(ApiMessages.AMOUNT_NEGATIVE_ERROR);
		}
		if(amount > this.TRANSACTION_LIMIT) {
			throw new InvalidAmountException(ApiMessages.AMOUNT_EXCEEDED_TRANSACTION_LIMIT);
		} 
	}

	private void validateFunds(double sourceAccountBalance, double amount) {
		if (sourceAccountBalance < amount) {
			throw new InsufficientBalanceException(ApiMessages.INSUFFICIENT_BALANCE_ERROR);
		}
	}
}