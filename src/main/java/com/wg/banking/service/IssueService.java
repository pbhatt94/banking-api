package com.wg.banking.service;

import java.util.List;

import com.wg.banking.dto.IssueDto;
import com.wg.banking.dto.IssueResponse;
import com.wg.banking.dto.UpdateIssueRequest;
import com.wg.banking.filter.IssuesFilter;


public interface IssueService {
	public List<IssueResponse> getAllIssuesByUserId(String userId, IssuesFilter filter);
	
	public List<IssueResponse> getAllIssues(IssuesFilter filter);
	
	public IssueResponse createIssue(IssueDto issueDto);
	
	public IssueResponse updateIssue(String issueId, UpdateIssueRequest issueDto);
}