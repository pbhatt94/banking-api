package com.wg.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wg.banking.constants.ApiMessages;
import com.wg.banking.dto.ApiResponseHandler;
import com.wg.banking.dto.IssueDto;
import com.wg.banking.dto.IssueResponse;
import com.wg.banking.dto.UpdateIssueRequest;
import com.wg.banking.filter.IssuesFilter;
import com.wg.banking.model.ApiResponseStatus;
import com.wg.banking.model.IssuePriority;
import com.wg.banking.model.IssueStatus;
import com.wg.banking.service.IssueService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/api")
public class IssueController {
	@Autowired
	private final IssueService issueService;

	@GetMapping("/api/issues")
	public ResponseEntity<Object> getAllIssues(
			@RequestParam(required = false) IssuePriority priority,
			@RequestParam(required = false) IssueStatus status, 
			@RequestParam(required = false) String user_name) {
		IssuesFilter filter = IssuesFilter.builder().priority(priority).status(status).user_name(user_name).build();

		List<IssueResponse> issues = issueService.getAllIssues(filter);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.OK,
				ApiMessages.ISSUES_FETCHED_SUCCESSFUL_MESSAGE, issues);
	}

	@GetMapping("/api/user/{userId}/issues")
	public ResponseEntity<Object> getAllIssuesByUserId(
			@PathVariable String userId,
			@RequestParam(required = false) IssuePriority priority, 
			@RequestParam(required = false) IssueStatus status,
			@RequestParam(required = false) String user_name) {
		IssuesFilter filter = IssuesFilter.builder().priority(priority).status(status).user_name(user_name).build();

		List<IssueResponse> issues = issueService.getAllIssuesByUserId(userId, filter);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.OK,
				ApiMessages.ISSUES_FETCHED_SUCCESSFUL_MESSAGE, issues);
	}
	
	@PostMapping("/api/issues")
	public ResponseEntity<Object> createIssue(@Valid @RequestBody IssueDto issueDto) {
		IssueResponse issue = issueService.createIssue(issueDto);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.CREATED,
				ApiMessages.ISSUE_RAISED_SUCCESSFULLY, issue);
	}
	
	@PutMapping("/api/issues/{issueId}")
	public ResponseEntity<Object> updateIssue(@PathVariable String issueId, @Valid @RequestBody UpdateIssueRequest request) {
		IssueResponse updatedIssue = issueService.updateIssue(issueId, request);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.OK,
				ApiMessages.ISSUE_UPDATED_SUCCESSFUL_MESSAGE, updatedIssue);
	}
}
