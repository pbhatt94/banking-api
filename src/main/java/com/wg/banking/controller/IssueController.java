package com.wg.banking.controller;

import com.wg.banking.constants.StringConstants;
import com.wg.banking.helper.printer.IssuePrinter;
import com.wg.banking.model.Branch;
import com.wg.banking.model.Issue;
import com.wg.banking.service.IssueService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IssueController {
    	private IssueService issueService;
    private Scanner scanner;
    
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
        this.scanner = new Scanner(System.in);
    }

    public void addIssue(Issue issue) {
    	boolean isIssueRaised = issueService.addIssue(issue);
    	
    	if(isIssueRaised)
    		System.out.println(StringConstants.ISSUE_RAISED_SUCCESSFULLY);
    	else 
    		System.out.println(StringConstants.ISSUE_COULD_NOT_BE_RAISED);
    }

    public Issue getIssueById() {
    	try {
            System.out.print(StringConstants.ENTER_ISSUE_ID);
            String issueId = scanner.nextLine();

            Issue issue = issueService.getIssueById(issueId);
            return issue;
        } catch (Exception e) {
            System.out.println(StringConstants.ERROR_RETRIEVING_ISSUE + e.getMessage());
        }
    	return null;
    }

    public List<Issue> getAllIssues() {
    	List<Issue> issues = new ArrayList<>();
    	try {
    		issues = issueService.getAllIssues();
        } catch (Exception e) {
            System.out.println(StringConstants.ERROR_RETRIEVING_ISSUE + e.getMessage());
        }
    	return issues;
    }
    
    public List<Issue> getAllIssuesByBranch(Branch branch) {
    	List<Issue> issues = new ArrayList<>();
    	try {
    		issues = issueService.getAllIssuesByBranch(branch);
    	} catch (Exception e) {
    		System.out.println(StringConstants.ERROR_RETRIEVING_ISSUE + e.getMessage());
    	}
    	return issues;
    }
    
    
    
    public void getAllIssues(String userId) {
    	try {
            List<Issue> issues = issueService.getAllIssues(userId);            
            IssuePrinter.printIssues(issues);
        } catch (Exception e) {
            System.out.println(StringConstants.ERROR_RETRIEVING_ISSUE + e.getMessage());
        }
    }
    
    public void resolveIssue(Issue issue) {
    	try {
			issueService.resolveIssue(issue);
		} catch (Exception e) {
            System.out.println(StringConstants.ERROR_RETRIEVING_ISSUE + e.getMessage());
            e.printStackTrace();
		}
    }
}
