package com.wg.banking.mapper;

import com.wg.banking.dto.IssueResponse;
import com.wg.banking.model.Issue;

public class IssueMapper {
	public static IssueResponse mapIssueToIssueResponse(Issue issue) {
		IssueResponse issueResponse = new IssueResponse();
		issueResponse.setId(issue.getId());
		issueResponse.setCreatedAt(issue.getCreatedAt());
		issueResponse.setDescription(issue.getDescription());
		issueResponse.setPriority(issue.getPriority());
		issueResponse.setStatus(issue.getStatus());
		issueResponse.setTitle(issue.getTitle());
		issueResponse.setUpdatedAt(issue.getUpdatedAt());
		issueResponse.setUser(UserMapper.mapUser(issue.getUser()));
		return issueResponse;
	}
}
