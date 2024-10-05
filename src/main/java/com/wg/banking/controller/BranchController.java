//package com.wg.banking.controller;
//
//import com.wg.banking.constants.BranchConstants;
//import com.wg.banking.constants.StringConstants;
//import com.wg.banking.dao.UserDAO;
//import com.wg.banking.dao.impl.UserDAOImpl;
//import com.wg.banking.helper.GetUserInput;
//import com.wg.banking.helper.printer.BranchPrinter;
//import com.wg.banking.helper.printer.UserPrinter;
//import com.wg.banking.model.Branch;
//import com.wg.banking.model.User;
//import com.wg.banking.service.BranchService;
//import com.wg.banking.service.UserService;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Scanner;
//
//public class BranchController {
//
//	private BranchService branchService;
//    private Scanner scanner;
//    private static UserDAO userDAO = new UserDAOImpl();
//    private static UserService userService = new UserService(userDAO);
//    
//    public BranchController(BranchService branchService) {
//        this.branchService = branchService;
//        this.scanner = new Scanner(System.in);
//    }
//
//    public void addBranch(Branch branch) {
//        branchService.addBranch(branch);
//    } 
// 
//    public void getBranchById() {
//    	try {
//            System.out.print(StringConstants.ENTER_BRANCH_ID);
//            String branchId = scanner.nextLine();
//
//            Branch branch = branchService.getBranchById(branchId);
//            if (branch == null) {
//            	System.out.println(StringConstants.BRANCH_NOT_FOUND);
//            	return;
//            } 
//        } catch (Exception e) {
//            System.out.println(StringConstants.ERROR_RETRIEVING_BRANCH + e.getMessage());
//        }
//    }
//
//    public List<Branch> getAllBranches() {
//    	List<Branch> branches = new ArrayList<>();
//    	try {
//            branches = branchService.getAllBranches();
//           
//        } catch (Exception e) {
//            System.out.println(StringConstants.ERROR_RETRIEVING_BRANCH + e.getMessage());
//        }
//    	return branches;
//    }
//
//    public void updateBranch() {
//    	try {
//    		int branchIndex;
//			List<Branch> branches = getAllBranches();
//			BranchPrinter.printBranches(branches);
//    		
//            System.out.print(StringConstants.ENTER_INDEX_MESSAGE);
//            branchIndex = GetUserInput.getUserChoice();
//            
//            while(branchIndex <= 0 || branchIndex > branches.size()) {
//            	System.out.println(StringConstants.INVALID_INDEX_MESSAGE);
//            	System.out.print(StringConstants.ENTER_INDEX_MESSAGE);
//                branchIndex = GetUserInput.getUserChoice();
//            }
//             
//            String branchId = branches.get(branchIndex-1).getBranchId();
//
//            Branch branch = branchService.getBranchById(branchId);
//            
//            if (branch == null) {
//                System.out.println(StringConstants.BRANCH_NOT_FOUND);
//                return;
//            } 
//            
//            boolean continueUpdating = true;
//
//            while (continueUpdating) {
//            	
//
//            	System.out.println(StringConstants.BRANCH_UPDATE_MENU);
//
//                System.out.print(StringConstants.SELECT_AN_OPTION);
//                int choice = scanner.nextInt();
//                scanner.nextLine(); 
//                
//                String columnToUpdate = "";
//
//                switch (choice) {
//                    case 1:
//                    	columnToUpdate = BranchConstants.BRANCH_NAME_COLUMN;
//                        System.out.print(StringConstants.ENTER_NEW_BRANCH_NAME);
//                        branch.setBranchName(scanner.nextLine());
//                        break;
//                    case 2:
//                    	columnToUpdate = BranchConstants.BRANCH_ADDRESS_COLUMN;
//                        System.out.print(StringConstants.ENTER_NEW_BRANCH_ADDRESS);
//                        branch.setBranchAddress(scanner.nextLine());
//                        break;
//                    case 3:
//                    	columnToUpdate = BranchConstants.BRANCH_MANAGER_COLUMN;
//                    	List<User> managers = userService.getAvailableManagers();
//                        if(managers.size() == 0) {
//                        	System.out.println(StringConstants.NO_AVAILABLE_MANAGERS_MESSAGE);
//                        	break;
//                        }
//                        
//                        UserPrinter.printUsers(managers);
//                        System.out.println(StringConstants.ENTER_INDEX_MESSAGE);
//                        int index = GetUserInput.getUserChoice();
//                       
//                        
//                        while(index <= 0 || index > managers.size()) {
//                        	System.out.println(StringConstants.INVALID_INDEX_MESSAGE);
//                        	System.out.println((StringConstants.ENTER_INDEX_MESSAGE));
//                        	index = GetUserInput.getUserChoice();
//                        }
//                        
//                        String branchManagerId = managers.get(index-1).getUserId();
//                        branch.setBranchManagerId(branchManagerId);
//                        break;
//                    case 4:
//                        continueUpdating = false;
//                        break;
//                    default:
//                        System.out.println(StringConstants.INVALID_SWITCH_CASE_INPUT);
//                }
//                if(!continueUpdating) break;
//                branch.setUpdatedAt(new Date());
//                branchService.updateBranch(branch, columnToUpdate);
//                System.out.println(StringConstants.BRANCH_UPDATED_SUCCESSFULLY);
//            }
//        } catch (Exception e) {
//            System.out.println(StringConstants.ERROR_UPDATING_BRANCH + e.getMessage());
//        }
//    }
//
//
//    public void deleteBranch() {
//    	try {
//    		int branchIndex;
//			List<Branch> branches = getAllBranches();
//			BranchPrinter.printBranches(branches);
//			if(branches.size() == 0) {
//				System.out.println(StringConstants.THERE_ARE_NO_BRANCHES);
//				return;
//			}
//    		
//            System.out.print(StringConstants.ENTER_INDEX_MESSAGE);
//            branchIndex = GetUserInput.getUserChoice();
//            
//            while(branchIndex <= 0 || branchIndex > branches.size()) {
//            	System.out.println(StringConstants.INVALID_INDEX_MESSAGE);
//            	System.out.print(StringConstants.ENTER_INDEX_MESSAGE);
//                branchIndex = GetUserInput.getUserChoice();
//            }
//            
//            String branchId = branches.get(branchIndex-1).getBranchId();
//                        
//            boolean isBranchDeleted = branchService.deleteBranch(branchId);
//			if(isBranchDeleted) {
//                System.out.println(StringConstants.BRANCH_DELETED_SUCCESSFULLY);
//            } else {
//            	System.out.println(StringConstants.BRANCH_CAN_T_BE_DELETED_MESSAGE);
//            }            
//        } catch (Exception e) {
//            System.out.println(StringConstants.ERROR_DELETING_BRANCH + e.getMessage());
//        }
//    }
//    
//    public Branch getBranchByManagerId(User user) {
//    	return branchService.getBranchByManagerId(user);
//    }
//}
