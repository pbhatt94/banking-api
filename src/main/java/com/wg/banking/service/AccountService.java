package com.wg.banking.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.wg.banking.dao.AccountDAO;
import com.wg.banking.helper.LoggingUtil;
import com.wg.banking.model.Account;
import com.wg.banking.model.User;

public class AccountService {
	private AccountDAO accountDAO;
	private static Logger logger = LoggingUtil.getLogger(AccountService.class);

	public AccountService(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	public List<Account> getAllAccounts() {
		try {
			return accountDAO.getAllAccounts();
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public List<Account> getAllAccounts(String branchId) {
		try {
			return accountDAO.getAllAccounts(branchId);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		} 
		return null;
	}

	public boolean addAccount(Account account) {
		try {
			if (account == null) {
				return false;
			}
			Account oldAccount = getAccount(account.getAccountNo());
			if (oldAccount != null) {
				return false;
			}

			List<Account> allAccounts = getAllAccounts();
			for (Account currentAccount : allAccounts) {
				if (currentAccount.getOwnerId().equals(account.getOwnerId())) {
					return false;
				}
			}

			accountDAO.addAccount(account);
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateAccount(Account account) {
		try {
			return accountDAO.updateAccount(account);
		} catch (ClassNotFoundException | SQLException e) { 
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public void deleteAccount(String branchId) {
		try {
			accountDAO.deleteAccount(branchId);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
	}

	public Account getAccount(String accountNo) {
		try {
			return accountDAO.getAccount(accountNo);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public Account getAccount(User user) {
		try {
			return accountDAO.getAccount(user);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public Account getAccountById(String accountNumber) {
		try {
			return accountDAO.getUser(accountNumber);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}