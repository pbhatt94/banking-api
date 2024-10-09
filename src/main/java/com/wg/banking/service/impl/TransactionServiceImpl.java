package com.wg.banking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wg.banking.constants.ApiMessages;
import com.wg.banking.exception.ResourceAccessDeniedException;
import com.wg.banking.exception.UserNotFoundException;
import com.wg.banking.model.Transaction;
import com.wg.banking.model.User;
import com.wg.banking.repository.TransactionRepository;
import com.wg.banking.service.TransactionService;
import com.wg.banking.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
	private final TransactionRepository transactionRepository;
	private final UserService userService;
	
	@Override
	public List<Transaction> getAllTransactionsByUserId(String userId) {
		User user = userService.getCurrentUser();
		if(user == null) {
			throw new UserNotFoundException(ApiMessages.USER_NOT_FOUND_ERROR);
		}
		if(!user.getUserId().equalsIgnoreCase(userId)) {
			throw new ResourceAccessDeniedException(ApiMessages.ACESS_DENIED_ERROR);
		}
		String accountNumber = user.getAccount().getAccountNumber();
		List<Transaction> transactions = transactionRepository.findBySourceAccount_AccountNumberOrTargetAccount_AccountNumber(accountNumber, accountNumber);
		transactions = transactions.stream().sorted((t1, t2) -> t2.getCreatedAt().compareTo(t1.getCreatedAt()))
                .collect(Collectors.toList());
		return transactions;
	}
}