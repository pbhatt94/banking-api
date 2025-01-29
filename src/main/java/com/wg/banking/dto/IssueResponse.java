package com.wg.banking.dto;

import java.time.LocalDateTime;

import com.wg.banking.model.Issue;
import com.wg.banking.model.IssuePriority;
import com.wg.banking.model.IssueStatus;
import com.wg.banking.service.IssueService;

import lombok.Data;

@Data
public class IssueResponse {
	private String id;

	private String title;

	private String description;

	private IssueStatus status;
	private IssuePriority priority;

	private UserDto user;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}