package com.wg.banking.filter;

import com.wg.banking.model.IssuePriority;
import com.wg.banking.model.IssueStatus;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
public class IssuesFilter {
	private IssuePriority priority;
	private IssueStatus status;
	private String user_name;
}
