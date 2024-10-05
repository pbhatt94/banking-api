package com.wg.banking.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.wg.banking.dao.AccountDetailsDAO;
import com.wg.banking.helper.LoggingUtil;
import com.wg.banking.model.AccountDetails;

public class AccountDetailsService {
	private AccountDetailsDAO accountDetailsDAO;
	private static Logger logger = LoggingUtil.getLogger(AccountDetailsService.class);

	public AccountDetailsService(AccountDetailsDAO accountDetailsDAO) {
		this.accountDetailsDAO = accountDetailsDAO;
	}

	public List<AccountDetails> getAllAccountDetailsByMinimumBalance(List<AccountDetails> accounts, double minBalance) {
		List<AccountDetails> requiredAccounts = new ArrayList<>();
		for (AccountDetails account : accounts) {
			if (account.getBalance() >= minBalance) {
				requiredAccounts.add(account);
			}
		}
		return requiredAccounts;
	}

	public List<AccountDetails> getAllAccountDetailsByMaximumBalance(List<AccountDetails> accounts, double maxBalance) {
		List<AccountDetails> requiredAccounts = new ArrayList<>();
		for (AccountDetails account : accounts) {
			if (account.getBalance() <= maxBalance) {
				requiredAccounts.add(account);
			}
		}
		return requiredAccounts;
	} 

	public List<AccountDetails> getAllAccountDetailsByBalanceWithinRange(List<AccountDetails> accounts,
			double minBalance, double maxBalance) {
		List<AccountDetails> requiredAccounts = new ArrayList<>();
		for (AccountDetails account : accounts) {
			if (account.getBalance() >= minBalance && account.getBalance() <= maxBalance) {
				requiredAccounts.add(account);
			}
		}
		return requiredAccounts;
	}

	public List<AccountDetails> getAllAccountDetails(String branchName) {
		List<AccountDetails> requiredAccounts = new ArrayList<>();
		try {
			List<AccountDetails> accounts = accountDetailsDAO.getAllAccountDetails();

			for (AccountDetails account : accounts) {
				if (account.getBranch().equalsIgnoreCase(branchName)) {
					requiredAccounts.add(account);
				}
			}
			return requiredAccounts;
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public List<AccountDetails> getAllAccountDetails() {
		try {
			return accountDetailsDAO.getAllAccountDetails();
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return null;
	} 

}
