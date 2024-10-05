package com.wg.banking.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.wg.banking.constants.StringConstants;
import com.wg.banking.dao.AccountDAO;
import com.wg.banking.dao.IssueDAO;
import com.wg.banking.dao.impl.AccountDAOImpl;
import com.wg.banking.helper.LoggingUtil;
import com.wg.banking.model.Account;
import com.wg.banking.model.Branch;
import com.wg.banking.model.Issue;

public class IssueService {
	private IssueDAO issueDAO;
	private AccountDAO accountDAO;
	private AccountService accountService;

	private static Logger logger = LoggingUtil.getLogger(IssueService.class);

	public IssueService(IssueDAO issueDAO) {
		this.issueDAO = issueDAO;
		accountDAO = new AccountDAOImpl();
		accountService = new AccountService(accountDAO);
	}
 
	public IssueService(IssueDAO issueDAO, AccountService accountService) {
		this.issueDAO = issueDAO;
		this.accountService = accountService;
	}

	public List<Issue> getAllIssuesByBranch(Branch branch) {
		if (branch == null) {
			System.out.println(StringConstants.BRANCH_CAN_T_BE_NULL);
			return null;
		}

		List<Account> accounts = accountService.getAllAccounts(branch.getBranchId());

		Set<String> userIds = accounts.stream().map(account -> account.getOwnerId()).collect(Collectors.toSet());

		List<Issue> allIssues = getAllIssues();

		List<Issue> filteredIssues = allIssues.stream().filter(issue -> userIds.contains(issue.getUserId()))
				.collect(Collectors.toList());
		return filteredIssues;
	}

	public void resolveIssue(Issue issue) {
		try {
			if (issue.getStatus() == Issue.Status.RESOLVED) {
				System.out.println(StringConstants.ISSUE_ALREADY_RESOLVED);
				return;
			}
			issue.setStatus(Issue.Status.RESOLVED);
			issueDAO.resolveIssue(issue);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Issue> getAllIssues(String userId) {
		try {
			List<Issue> issues = issueDAO.getAllIssues();
			Predicate<? super Issue> isIssueRelatedToUser = issue -> issue.getUserId().equals(userId);
			return issues.stream().filter(isIssueRelatedToUser).collect(Collectors.toList());
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public Issue getIssueById(String issueId) {
		try {
			return issueDAO.getIssueById(issueId);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public List<Issue> getAllIssues() {
		try {
			return issueDAO.getAllIssues();
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public boolean addIssue(Issue issue) {
		try {
			return issueDAO.addIssue(issue);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

}