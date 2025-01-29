package com.wg.banking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.wg.banking.constants.ApiMessages;
import com.wg.banking.exception.ResourceAccessDeniedException;
import com.wg.banking.exception.UserNotFoundException;
import com.wg.banking.model.Transaction;
import com.wg.banking.model.TransactionType;
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
	public List<Transaction> getAllTransactionsByUserId(String accountId, Integer pageNumber, Integer pageSize,
			String transactionType, Double minAmount, Double maxAmount) {
		if (pageNumber < 0 || pageSize <= 0)
			throw new InvalidInputException(ApiMessages.INVALID_PAGE_NUMBER_OR_LIMIT);
		User user = userService.getCurrentUser();
		validateUser(accountId, user);
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Order.desc("createdAt")));
		Specification<Transaction> spec = (root, query, cb) -> cb.or(
				cb.equal(root.get("sourceAccount").get("id"), accountId),
				cb.equal(root.get("targetAccount").get("id"), accountId));

		if (transactionType != null) {
			spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("transactionType"),
					TransactionType.valueOf(transactionType)));
		}

		if (minAmount != null) {
			spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("amount"),
					minAmount));
		}

		if (maxAmount != null) {
			spec = spec.and(
					(root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("amount"), maxAmount));
		}

		return transactionRepository.findAll(spec, pageable).getContent();
	}

	private List<Transaction> sortTransactions(List<Transaction> transactions) {
		return transactions.stream().sorted((t1, t2) -> t2.getCreatedAt().compareTo(t1.getCreatedAt()))
				.collect(Collectors.toList());
	}

	@Override
	public List<Transaction> getAllTransactions(Integer pageNumber, Integer pageSize, String transactionType,
			Double minAmount, Double maxAmount) {
		if (pageNumber < 0 || pageSize <= 0)
			throw new InvalidInputException(ApiMessages.INVALID_PAGE_NUMBER_OR_LIMIT);

		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Order.desc("createdAt")));
		Specification<Transaction> spec = Specification.where(null);

		if (transactionType != null) {
			spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("transactionType"),
					TransactionType.valueOf(transactionType)));
		}

		if (minAmount != null) {
			spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("amount"),
					minAmount));
		}

		if (maxAmount != null) {
			spec = spec.and(
					(root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("amount"), maxAmount));
		}

		return transactionRepository.findAll(spec, pageable).getContent();
	}

	private void validateUser(String accountId, User user) {
		if (user == null) {
			throw new UserNotFoundException(ApiMessages.USER_NOT_FOUND_ERROR);
		}
		if (!user.getAccount().getId().equalsIgnoreCase(accountId)) {
			throw new ResourceAccessDeniedException(ApiMessages.ACESS_DENIED_ERROR);
		}
	}

}