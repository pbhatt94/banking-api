package com.wg.banking.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wg.banking.constants.ApiMessages;
import com.wg.banking.dto.IssueDto;
import com.wg.banking.dto.IssueResponse;
import com.wg.banking.dto.UpdateIssueRequest;
import com.wg.banking.exception.IllegalResourceException;
import com.wg.banking.exception.IssueNotFoundException;
import com.wg.banking.exception.UserNotFoundException;
import com.wg.banking.filter.IssuesFilter;
import com.wg.banking.mapper.IssueMapper;
import com.wg.banking.model.Issue;
import com.wg.banking.model.IssuePriority;
import com.wg.banking.model.User;
import com.wg.banking.repository.IssueRepository;
import com.wg.banking.repository.UserRepository;
import com.wg.banking.service.IssueService;
import com.wg.banking.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class IssueServiceImpl implements IssueService {

	@Autowired
	private final IssueRepository issueRepository;

	@Autowired
	private final UserService userService;

	@Autowired
	private final UserRepository userRepository;

	@Override
	public List<IssueResponse> getAllIssuesByUserId(String userId, IssuesFilter filter) {
		List<Issue> allIssues = issueRepository.findAllByUser_UserId(userId);
		allIssues = sortIssues(allIssues);
		allIssues = filterIssues(filter, allIssues);
		List<IssueResponse> issues = new ArrayList<>();
		for(Issue issue: allIssues) {
			issues.add(IssueMapper.mapIssueToIssueResponse(issue));
		}
		return issues;
	}

	@Override
	public List<IssueResponse> getAllIssues(IssuesFilter filter) {
		List<Issue> allIssues = issueRepository.findAll();
		allIssues = sortIssues(allIssues);
		allIssues = filterIssues(filter, allIssues);
		List<IssueResponse> issues = new ArrayList<>();
		for(Issue issue: allIssues) {
			issues.add(IssueMapper.mapIssueToIssueResponse(issue));
		}
		return issues;
	}

	@Override
	public IssueResponse createIssue(IssueDto issueDto) {
		Optional<User> user = userRepository.findById(issueDto.getUserId());
		if (user.isEmpty()) {
			throw new UserNotFoundException(ApiMessages.USER_NOT_FOUND_ERROR);
		}
		User currentUser = userService.getCurrentUser();
		if (currentUser.getUserId() != user.get().getUserId()) {
			throw new IllegalResourceException(ApiMessages.ACTION_NOT_ALLOWED);
		}
		Issue issue = new Issue();

		issue.setTitle(issueDto.getTitle());
		issue.setDescription(issueDto.getDescription());
		issue.setUser(currentUser);
		issue.setPriority(IssuePriority.LOW);
		if (issueDto.getPriority() != null)
			issue.setPriority(issueDto.getPriority());
		Issue savedIssue = issueRepository.save(issue);
		return IssueMapper.mapIssueToIssueResponse(savedIssue);
	}

	private List<Issue> filterIssues(IssuesFilter filter, List<Issue> allIssues) {
		if (filter != null) {
			if (filter.getPriority() != null) {
				allIssues = allIssues.stream().filter(issue -> issue.getPriority() == filter.getPriority()).toList();
			}

			if (filter.getStatus() != null) {
				allIssues = allIssues.stream().filter(issue -> issue.getStatus() == filter.getStatus()).toList();
			}

			if (filter.getUser_name() != null) {
				allIssues = allIssues
						.stream().filter(issue -> issue.getUser() != null && issue.getUser().getName() != null && issue
								.getUser().getName().toLowerCase().contains(filter.getUser_name().toLowerCase()))
						.toList();
			}

		}
		return allIssues;
	}

	private List<Issue> sortIssues(List<Issue> allIssues) {
		return allIssues.stream().sorted(Comparator.comparing(Issue::getCreatedAt).reversed()).toList();
	}

	@Override
	public IssueResponse updateIssue(String issueId, UpdateIssueRequest issueDto) {
		Optional<Issue> existingIssue = issueRepository.findById(issueId);
		if (existingIssue.isEmpty()) {
			throw new IssueNotFoundException(ApiMessages.ISSUE_NOT_FOUND);
		}
		Issue issue = existingIssue.get();
		issue.setStatus(issueDto.getStatus());
		Issue savedIssue = issueRepository.save(issue);
		return IssueMapper.mapIssueToIssueResponse(savedIssue);
	}

}
