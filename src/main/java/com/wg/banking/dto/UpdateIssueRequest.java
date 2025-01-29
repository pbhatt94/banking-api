package com.wg.banking.dto;

import com.wg.banking.model.IssueStatus;

import lombok.Data;

@Data
public class UpdateIssueRequest {
	private IssueStatus status;
}
