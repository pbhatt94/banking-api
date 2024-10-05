//package com.wg.banking.controller;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Scanner;
//
//import com.wg.banking.constants.StringConstants;
//import com.wg.banking.dao.BranchDAO;
//import com.wg.banking.dao.UserDAO;
//import com.wg.banking.dao.impl.UserDAOImpl;
//import com.wg.banking.dao.impl.BranchDAOImpl;
//import com.wg.banking.helper.GetUserInput;
//import com.wg.banking.helper.printer.UserPrinter;
//import com.wg.banking.model.Account;
//import com.wg.banking.model.Branch;
//import com.wg.banking.model.User;
//import com.wg.banking.service.AccountService;
//import com.wg.banking.service.BranchService;
//import com.wg.banking.service.UserService;
//
//public class AccountController {
//
//	private AccountService accountService;
//    private Scanner scanner;
//    private UserDAO userDAO;
//    private UserService userService;
//    private BranchDAO branchDAO;
//    private BranchService branchService;
//    
//
//    public AccountController(AccountService accountService) {
//        this.accountService = accountService;
//        this.scanner = new Scanner(System.in); 
//        userDAO = new UserDAOImpl(); 
//        userService = new UserService(userDAO);
//        branchDAO = new BranchDAOImpl();
//        branchService = new BranchService(branchDAO);
//    }
//
//    public void addAccount(Account account) {        
//        accountService.addAccount(account);
//    }
//
//    public Account getAccount(String accountNo) {
//    	Account account = null;
//    	try {
//            account = accountService.getAccount(accountNo);
//        } catch (Exception e) {
//            System.out.println(StringConstants.ERROR_RETRIEVING_ACCOUNT + e.getMessage());
//        }
//    	return account;
//    }
//    
//    public Account getAccount(User user) {
//    	Account account = null;
//    	try {
//    		account = accountService.getAccount(user);
//        } catch (Exception e) {
//            System.out.println(StringConstants.ERROR_RETRIEVING_ACCOUNT + e.getMessage());
//        }
//    	return account;
//    }
//
//    public List<Account> getAllAccounts() {
//    	List<Account> accounts = new ArrayList<>();
//    	try {
//    		accounts = accountService.getAllAccounts();
//        } catch (Exception e) {
//            System.out.println(StringConstants.ERROR_RETRIEVING_ACCOUNT + e.getMessage());
//        }
//    	return accounts;
//    }
//    
//    public List<Account> getAllAccounts(String branchId) {
//    	try {
//            List<Account> accounts = accountService.getAllAccounts(branchId);
//            return accounts;
//        } catch (Exception e) {
//            System.out.println(StringConstants.ERROR_RETRIEVING_ACCOUNT + e.getMessage());
//        }
//    	return null;
//    }
//
//    public void updateAccount() {
//    	try {
//            System.out.println(StringConstants.SELECT_ACCOUNT_TO_UPDATE);
//            List<Account> accounts = accountService.getAllAccounts();
//            int accountIndex = 1;
//            for(Account account: accounts) {
//            	System.out.println(accountIndex + "   " + account.getAccountNo());
//            	accountIndex++;
//            }
//            
//            System.out.println(StringConstants.ENTER_INDEX_MESSAGE);
//        	accountIndex = GetUserInput.getUserChoice();
//            
//            while(accountIndex <=0 || accountIndex > accounts.size()) {
//            	System.out.println(StringConstants.INVALID_INDEX_MESSAGE);
//            	System.out.println(StringConstants.ENTER_INDEX_MESSAGE);
//            	accountIndex = GetUserInput.getUserChoice();
//            }
//            
//            String accountNo = accounts.get(accountIndex-1).getAccountNo();
//            Account account = accountService.getAccount(accountNo);
//            if (account == null) {
//            	System.out.println(StringConstants.ACCOUNT_NOT_FOUND);
//            	return;
//            }
//            boolean continueUpdating = true;
//
//            while (continueUpdating) {
//            	
//            	
//            	System.out.println(StringConstants.ACCOUNT_UPDATE_MENU);
//
//                System.out.print(StringConstants.SELECT_AN_OPTION);
//                int choice = GetUserInput.getUserChoice();
//                
//                switch (choice) {
//                    case 1:
//                        System.out.print(StringConstants.SELECT_NEW_ACCOUNT_OWNER);
//                        List<User> users = userService.getAvailableUsers();
//                        UserPrinter.printUsers(users);
//                        System.out.println(StringConstants.ENTER_INDEX_MESSAGE);
//                        int index = GetUserInput.getUserChoice();
//                        
//                        while(index <=0 || index > users.size()) {
//                        	System.out.println(StringConstants.INVALID_INDEX_MESSAGE);
//                        	System.out.println(StringConstants.ENTER_INDEX_MESSAGE);
//                            index = GetUserInput.getUserChoice();
//                        }
//                        
//                        String ownerId = users.get(index-1).getUserId();
//                        account.setOwnerId(ownerId);
//                        break;
//                    case 2:
//                    	System.out.println(StringConstants.AVAILABLE_BRANCHES_MESSAGE);
//                    	List<Branch> branches = branchService.getAllBranches();
//                    	int branchIndex = 1;
//                    	for(Branch branch: branches) {
//                    		System.out.println(branchIndex + "   " + branch.getBranchName());
//                    		branchIndex++;
//                    	}
//                    	System.out.println(StringConstants.ENTER_INDEX_MESSAGE);
//                    	branchIndex = GetUserInput.getUserChoice();
//                        
//                        while(branchIndex <=0 || branchIndex > branches.size()) {
//                        	System.out.println(StringConstants.INVALID_INDEX_MESSAGE);
//                        	System.out.println(StringConstants.ENTER_INDEX_MESSAGE);
//                        	branchIndex = GetUserInput.getUserChoice();
//                        }
//                    	
//                        String branchId = branches.get(branchIndex-1).getBranchId();
//                        account.setBranchId(branchId);
//                        break;
//                    case 3:
//                        continueUpdating = false;
//                        break;
//                    default:
//                        System.out.println(StringConstants.INVALID_SWITCH_CASE_INPUT);
//                }
//                if(!continueUpdating) break;
//                account.setUpdatedAt(new Date());
//                accountService.updateAccount(account);
//                System.out.println(StringConstants.ACCOUNT_UPDATION_SUCCESSFUL);
//            }
//        } catch (Exception e) {
//            System.out.println(StringConstants.ERROR_RETRIEVING_ACCOUNT + e.getMessage());
//        }
//    }
//
//    public void deleteAccount() {
//    	try {
//            System.out.print(StringConstants.ENTER_ACCOUNT_NUMBER);
//            String accountNo = scanner.nextLine();
//            
//            Account account = accountService.getAccount(accountNo);
//            
//            if(account != null) {
//            	accountService.deleteAccount(accountNo);
//            } else {
//            	System.out.println(StringConstants.ACCOUNT_NOT_FOUND);
//            }            
//        } catch (Exception e) {
//            System.out.println(StringConstants.ERROR_RETRIEVING_ACCOUNT + e.getMessage());
//        }
//    }  
//    
//    
//    public Account getAccountById(String accountNumber) {
//    	return accountService.getAccountById(accountNumber);
//    }
//}