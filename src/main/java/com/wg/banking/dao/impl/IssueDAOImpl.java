package com.wg.banking.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.wg.banking.constants.IssueConstants;
import com.wg.banking.dao.GenericDAO;
import com.wg.banking.dao.IssueDAO;
import com.wg.banking.model.Issue;

public class IssueDAOImpl extends GenericDAO<Issue> implements IssueDAO {


	public IssueDAOImpl() {
		super();
	}

	@Override
	protected Issue mapResultSetToEntity(ResultSet resultSet) throws SQLException {
		Issue issue = new Issue();
		
		issue.setIssueID(resultSet.getString(IssueConstants.ISSUE_ID_COLUMN));
		issue.setMessage(resultSet.getString(IssueConstants.MESSAGE_COLUMN));
		issue.setUserId(resultSet.getString(IssueConstants.USER_ID_COLUMN));
		String status = resultSet.getString(IssueConstants.STATUS_COLUMN);
		issue.setStatus(Issue.Status.valueOf(status));
		issue.setCreatedAt(resultSet.getDate(IssueConstants.CREATED_AT_COLUMN));		
		return issue;
	}

	public Issue getIssueById(String issueId) throws ClassNotFoundException, SQLException {
		String query = String.format("SELECT * FROM %s WHERE %s = '%s'", IssueConstants.ISSUE_TABLE_NAME,
				IssueConstants.ISSUE_ID_COLUMN, issueId);
		return get(query);
	}

	public List<Issue> getAllIssues() throws ClassNotFoundException, SQLException {
		String query = String.format("SELECT * FROM %s", IssueConstants.ISSUE_TABLE_NAME);
		return getAll(query);
	}

	public List<Issue> getAllIssues(String userId) throws ClassNotFoundException, SQLException {
		String query = String.format("SELECT * FROM %s WHERE %s = '%s'", IssueConstants.ISSUE_TABLE_NAME,
				IssueConstants.USER_ID_COLUMN, userId);
		return getAll(query);
	}

	public boolean resolveIssue(Issue issue) throws ClassNotFoundException, SQLException {
		String query = String.format("UPDATE %s SET %s = '%s' WHERE %s = '%s'", IssueConstants.ISSUE_TABLE_NAME,
				IssueConstants.STATUS_COLUMN, issue.getStatus().toString(), IssueConstants.ISSUE_ID_COLUMN,
				issue.getIssueID());
		return update(query);
	}

	public boolean addIssue(Issue issue) throws ClassNotFoundException, SQLException {
		String query = String.format("INSERT INTO %s (%s, %s, %s, %s, %s) " + "VALUES ('%s', '%s', '%s', '%s', '%s')",
				IssueConstants.ISSUE_TABLE_NAME, IssueConstants.ISSUE_ID_COLUMN, IssueConstants.MESSAGE_COLUMN,
				IssueConstants.USER_ID_COLUMN, IssueConstants.STATUS_COLUMN, IssueConstants.CREATED_AT_COLUMN,
				issue.getIssueID(), issue.getMessage(), issue.getUserId(), issue.getStatus().toString(),
				new java.sql.Date(issue.getCreatedAt().getTime()));

		return update(query);
	}
}
