package com.wg.banking.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.wg.banking.constants.UserConstants;
import com.wg.banking.dao.AccountDAO;
import com.wg.banking.dao.BranchDAO;
import com.wg.banking.dao.UserDAO;
import com.wg.banking.dao.impl.UserDAOImpl;
import com.wg.banking.dao.impl.AccountDAOImpl;
import com.wg.banking.helper.LoggingUtil;
import com.wg.banking.model.Account;
import com.wg.banking.model.Branch;
import com.wg.banking.model.User;

public class BranchService {
	private BranchDAO branchDAO;
	private UserDAO userDAO;
	private UserService userService;
	private AccountService accountService;
	private AccountDAO accountDAO;

	private static Logger logger = LoggingUtil.getLogger(BranchService.class);

//	public BranchService(BranchDAO branchDAO) {
//		this.branchDAO = branchDAO;
//		userDAO = new UserDAOImpl();
//		userService = new UserService(userDAO); 
//		accountDAO = new AccountDAOImpl();
//		accountService = new AccountService(accountDAO);
//	}

	public BranchService(BranchDAO branchDAO, UserService userService, AccountService accountService) {
		this.branchDAO = branchDAO;
		this.userService = userService;
		this.accountService = accountService;
	}

	public boolean addBranch(Branch branch) {
		try {
			String managerId = branch.getBranchManagerId();
			User user = userService.getUserById(managerId);

			if (user == null || !isManager(user)) {
				return false;
			}

			List<Branch> branches = getAllBranches();
			for (Branch currentBranch : branches) {
				if (isSameBranch(branch, currentBranch)) {
					System.out.println("Branch already exists!");
					return false;
				}
			}

			boolean isBranchAdded = branchDAO.addBranch(branch);
			return isBranchAdded;
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	private boolean isSameBranch(Branch branch, Branch currentBranch) {
		return currentBranch.getBranchName().equalsIgnoreCase(branch.getBranchName());
	}

	private boolean isManager(User user) {
		return user.getRole().toString().equalsIgnoreCase(UserConstants.BRANCH_MANAGER);
	}

	public Branch getBranchById(String branchId) {
		try {
			return branchDAO.getBranchById(branchId);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public Branch getBranchByManagerId(User user) {
		Branch branch = null;
		try {
			// return null if the user is not a manager
			if (!isManager(user)) {
				return null;
			}

			branch = branchDAO.getBranchByManagerId(user.getUserId());
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return branch;
	}

	public List<Branch> getAllBranches() {
		try {
			return branchDAO.getAllBranches();
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public void updateBranch(Branch branch, String columnToUpdate) {
		try {
			branchDAO.updateBranch(branch, columnToUpdate);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
	}

	public boolean deleteBranch(String branchId) {
		try {
			// check if the branch contains any Account
			List<Account> accounts = accountService.getAllAccounts(branchId);
			if (accounts != null && !accounts.isEmpty()) {
				return false;
			}
			branchDAO.deleteBranch(branchId);
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
}