package com.wg.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wg.banking.model.Issue;


public interface IssueRepository extends JpaRepository<Issue, String> {
	public List<Issue> findAllByUser_UserId(String userId);
}