package com.wg.banking.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.wg.banking.constants.NotificationConstants;
import com.wg.banking.constants.StringConstants;
import com.wg.banking.constants.TransactionConstants;
import com.wg.banking.dao.AccountDAO;
import com.wg.banking.dao.NotificationDAO;
import com.wg.banking.dao.impl.AccountDAOImpl;
import com.wg.banking.dao.impl.NotificationDAOImpl;
import com.wg.banking.dao.TransactionDAO;
import com.wg.banking.helper.InsufficientBalanceException;
import com.wg.banking.helper.LoggingUtil;
import com.wg.banking.helper.UniqueIdGenerator;
import com.wg.banking.model.Account;
import com.wg.banking.model.Notification;
import com.wg.banking.model.Notification.NotificationType;
import com.wg.banking.model.Transaction;

public class TransactionService {

	private AccountService accountService;
	private AccountDAO accountDAO;

	private NotificationService notificationService;
	private NotificationDAO notificationDAO;

	private TransactionDAO transactionDAO;

	private static Logger logger = LoggingUtil.getLogger(TransactionService.class);

	public TransactionService(TransactionDAO transactionDAO) {
		this.transactionDAO = transactionDAO;
		accountDAO = new AccountDAOImpl();
		accountService = new AccountService(accountDAO);
		notificationDAO = new NotificationDAOImpl();
		notificationService = new NotificationService(notificationDAO);
	}

	public TransactionService(TransactionDAO transactionDAO, AccountService accountService,
			NotificationService notificationService) {
		this.transactionDAO = transactionDAO;
		this.accountService = accountService;
		this.notificationService = notificationService;
	}

	public void handleWithdrawal(Transaction transaction) {
		try {
			if (transaction == null) {
				return;
			}

			if (transaction.getAmount() <= 0) {
				System.out.println(StringConstants.INVALID_AMOUNT);
				return;
			}
			Account sourceAccount = accountService.getAccount(transaction.getSourceAccountId());

			if (sourceAccount == null) {
				System.out.println(
						StringConstants.NO_ACCOUNT_EXISTS_WITH_ACCOUNT_NUMBER + transaction.getDestinationAccountId());
				return;
			}

			double originalBalance = sourceAccount.getBalance();

			if (transaction.getAmount() > originalBalance) {
				throw new InsufficientBalanceException(StringConstants.NOT_ENOUGH_FUNDS);
			}

			double newBalance = originalBalance - transaction.getAmount();
			sourceAccount.setBalance(newBalance);
			accountService.updateAccount(sourceAccount);
			System.out.println(StringConstants.ACCOUNT_BALANCE_UPDATED);

			transactionDAO.addTransaction(transaction);

			// notify
			Notification notification = generateTransactionNotification(transaction, newBalance);
			notificationService.addNotification(notification);
		} catch (InsufficientBalanceException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
	}

	public void handleDeposit(Transaction transaction) {
		try {
			if (transaction == null) {
				return;
			}
			if (transaction.getAmount() <= 0) {
				System.out.println(StringConstants.INVALID_AMOUNT);
				return;
			}
			Account destinationAccount = accountService.getAccount(transaction.getDestinationAccountId());

			if (destinationAccount == null) {
				System.out.println(
						StringConstants.NO_ACCOUNT_EXISTS_WITH_ACCOUNT_NUMBER + transaction.getDestinationAccountId());
				return;
			}

			double originalBalance = destinationAccount.getBalance();

			double newBalance = originalBalance + transaction.getAmount();

			destinationAccount.setBalance(newBalance);

			accountService.updateAccount(destinationAccount);
			System.out.println(StringConstants.ACCOUNT_BALANCE_UPDATED);

			transactionDAO.addTransaction(transaction);

			// notify
			Notification notification = generateTransactionNotification(transaction, newBalance);
			notificationService.addNotification(notification);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
	}

	public void handleTransfer(Transaction transaction) {
		try {
			if (transaction == null) {
				return;
			}
			if (transaction.getAmount() <= 0) {
				System.out.println(StringConstants.INVALID_AMOUNT);
				return;
			}
			Account sourceAccount = accountService.getAccount(transaction.getSourceAccountId());

			if (sourceAccount == null) {
				System.out.println(
						StringConstants.NO_ACCOUNT_EXISTS_WITH_ACCOUNT_NUMBER + transaction.getDestinationAccountId());
				return;
			}

			double originalBalanceForSource = sourceAccount.getBalance();

			if (transaction.getAmount() > originalBalanceForSource) {
				throw new InsufficientBalanceException(StringConstants.NOT_ENOUGH_FUNDS);
			}

			Account destinationAccount = accountService.getAccount(transaction.getDestinationAccountId());
			if (destinationAccount == null) {
				System.out.println(
						StringConstants.NO_ACCOUNT_EXISTS_WITH_ACCOUNT_NUMBER + transaction.getDestinationAccountId());
				return;
			}

			if (sourceAccount.getAccountNo().equalsIgnoreCase(destinationAccount.getAccountNo())) {
				System.out.println(StringConstants.CAN_T_TRANSFER_TO_SAME_ACCOUNT);
				return;
			}

			double newBalanceForSource = originalBalanceForSource - transaction.getAmount();
			sourceAccount.setBalance(newBalanceForSource);
			accountService.updateAccount(sourceAccount);
			System.out.println(StringConstants.SOURCE_ACCOUNT_BALANCE_UPDATED);

			double originalBalanceForDestination = destinationAccount.getBalance();
			double newBalanceForDestination = originalBalanceForDestination + transaction.getAmount();
			destinationAccount.setBalance(newBalanceForDestination);
			accountService.updateAccount(destinationAccount);
			System.out.println(StringConstants.TARGET_ACCOUNT_BALANCE_UPDATED);

			transactionDAO.addTransaction(transaction);

			// notify
			Notification notification = generateTransactionNotification(transaction, newBalanceForSource);
			notificationService.addNotification(notification);
		} catch (InsufficientBalanceException e) {
			logger.fine(StringConstants.INSUFFICIENT_BALANCE + e.getMessage());
			;
			e.printStackTrace();
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
	}

	public Transaction getTransactionById(String transactionId) {
		try {
			return transactionDAO.getTransactionById(transactionId);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public List<Transaction> getAllTransactions() {
		try {
			return transactionDAO.getAllTransactions();
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public List<Transaction> getAllTransactions(String accountNo) {
		try {
			List<Transaction> transactions = transactionDAO.getAllTransactions(accountNo);
			return transactions;
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public List<Transaction> getAllTransactionsByBranch(String branchId) {
		List<Transaction> branchTransactions = new ArrayList<>();
		try {
			List<Transaction> transactions = transactionDAO.getAllTransactions();

			List<Account> accounts = accountService.getAllAccounts();

			Set<String> branchAccounts = accounts.stream().filter(account -> account.getBranchId().equals(branchId))
					.map(account -> account.getAccountNo()).collect(Collectors.toSet());

			branchTransactions = transactions.stream()
					.filter(transaction -> branchAccounts.contains(transaction.getSourceAccountId())
							|| branchAccounts.contains(transaction.getDestinationAccountId()))
					.collect(Collectors.toList());

		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return branchTransactions;
	}

	public void addTransaction(Transaction transaction) throws ClassNotFoundException, SQLException {
		String transactionType = transaction.getTransactionType().toString();
		switch (transactionType) {
		case TransactionConstants.WITHDRAWAL_TYPE:
			handleWithdrawal(transaction);
			break;
		case TransactionConstants.DEPOSIT_TYPE:
			handleDeposit(transaction);
			break;
		case TransactionConstants.TRANSFER_TYPE:
			handleTransfer(transaction);
			break;
		}
	}

	private Notification generateTransactionNotification(Transaction transaction, double newBalance) {
		Notification notification = new Notification();
		notification.setNotificationId(UniqueIdGenerator.generateUniqueId());
		NotificationType accountActivityType = Notification.NotificationType
				.valueOf(NotificationConstants.ACCOUNT_ACTIVITY_TYPE);
		notification.setNotificationType(accountActivityType);
		String transactionType = transaction.getTransactionType().toString();
		switch (transactionType) {
		case TransactionConstants.WITHDRAWAL_TYPE:
			String WITHDRAWAL_NOTIFICATION_MESSAGE = String.format(
					"Your withdrawal of $%.2f has been successfully processed.\n\n" + "\tTransaction Details:\n"
							+ "\t- Transaction ID: %s\n" + "\t- Date and Time: %s\n"
							+ "\t- Balance After Transaction: $%.2f\n\n"
							+ "\tThank you for using our banking services.",
					transaction.getAmount(), transaction.getTransactionId(), transaction.getCreatedAt(), newBalance);
			String userId = accountService.getAccountById(transaction.getSourceAccountId()).getOwnerId();
			System.out.println(userId);
			notification.setReceiverId(userId);
			notification.setMessage(WITHDRAWAL_NOTIFICATION_MESSAGE);
			break;
		case TransactionConstants.DEPOSIT_TYPE:
			String depositMessage = String.format("Your deposit of $%.2f has been successfully processed.\n\n"
					+ "\tTransaction Details:\n" + "\t- Transaction ID: %s\n" + "\t- Date and Time: %s\n"
					+ "\t- Balance After Transaction: $%.2f\n\n" + "\tThank you for using our banking services.",
					transaction.getAmount(), transaction.getTransactionId(), transaction.getCreatedAt(), newBalance);

			String user_id = accountService.getAccountById(transaction.getDestinationAccountId()).getOwnerId();
			notification.setReceiverId(user_id);
			notification.setMessage(depositMessage);
			break;
		case TransactionConstants.TRANSFER_TYPE:
			String transferMessage = String.format(
					"Your transfer of $%.2f has been successfully completed.\n\n" + "\tTransaction Details:\n"
							+ "\t- Transaction ID: %s\n" + "\t- Date and Time: %s\n" + "\t- Sent To: %s\n"
							+ "\t- Balance After Transaction: $%.2f\n\n"
							+ "\tThank you for using our banking services.",
					transaction.getAmount(), transaction.getTransactionId(), transaction.getCreatedAt(),
					transaction.getDestinationAccountId(), newBalance);
			String user_Id = accountService.getAccountById(transaction.getSourceAccountId()).getOwnerId();
			notification.setReceiverId(user_Id);
			notification.setMessage(transferMessage);
			break;
		}
		return notification;
	}
}
