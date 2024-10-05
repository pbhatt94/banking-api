package com.wg.banking.dao;

import java.sql.SQLException;
import java.util.List;

import com.wg.banking.model.Issue;

public interface IssueDAO {
	public Issue getIssueById(String issueId) throws ClassNotFoundException, SQLException;

	public List<Issue> getAllIssues() throws ClassNotFoundException, SQLException;

	public List<Issue> getAllIssues(String userId) throws ClassNotFoundException, SQLException;

	public boolean resolveIssue(Issue issue) throws ClassNotFoundException, SQLException;

	public boolean addIssue(Issue issue) throws ClassNotFoundException, SQLException;
}
