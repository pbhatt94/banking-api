//package com.wg.banking.controller;
//
//
//import java.util.logging.Logger;
//
//import com.wg.banking.constants.StringConstants;
//import com.wg.banking.constants.UserConstants;
//import com.wg.banking.dao.ClosedAccountsDAO;
//import com.wg.banking.dao.impl.ClosedAccountsDAOImpl;
//import com.wg.banking.helper.LoggingUtil;
//import com.wg.banking.helper.UnauthenticatedException;
//import com.wg.banking.model.ClosedAccounts;
//import com.wg.banking.model.User;
//import com.wg.banking.presentation.Menu;
//import com.wg.banking.presentation.StartMenu;
//import com.wg.banking.service.ClosedAccountsService;
//import com.wg.banking.service.LoginService;
//
//public class LoginController { 
//	
//	private static Logger logger = LoggingUtil.getLogger(LoginController.class);
//	private static ClosedAccountsDAO closedAccountsDAO = new ClosedAccountsDAOImpl();
//	private static ClosedAccountsService closedAccountsService = new ClosedAccountsService(closedAccountsDAO);
//	private static ClosedAccountsController closedAccountsController = new ClosedAccountsController(closedAccountsService);
//	
//	private LoginService loginService;
//	
//	public LoginController(LoginService loginService) {
//		this.loginService = loginService;
//	}
//	
//	public void authenticateUser(String username, String password) {
//		if(username == null || password == null) {
//			System.out.println(StringConstants.CREDENTIALS_CAN_T_BE_EMPTY);
//			StartMenu.handleSignIn();
//		}
//		
//		ClosedAccounts closedAccount = closedAccountsController.getClosedAccount(username);
//		if(closedAccount != null) {
//			System.out.println(StringConstants.ACCOUNT_ALREADY_CLOSED);
//			logger.info(StringConstants.FAILED_LOGIN_ATTEMPT_ACCOUNT_ALREADY_CLOSED_USERNAME + username);
//			StartMenu.showStartMenu();
//		}
//				
//		User user = loginService.authenticateUser(username, password);
//		try {
//			if(user == null)
//				throw new UnauthenticatedException(StringConstants.INVALID_CREDENTIALS);
//			
//			logger.info(StringConstants.USER_AUTHENTICATED_SUCCESSFULLY_USERNAME + username + "\n");
//			switch(user.getRole().toString()) {
//			case UserConstants.CUSTOMER: 
//				Menu.showCustomerMenu(user);
//				break;
//			case UserConstants.BRANCH_MANAGER:
//				Menu.showBranchManagerMenu(user);
//				break;
//			case UserConstants.ADMIN:
//				Menu.showAdminMenu(user);
//			}
//		} catch(UnauthenticatedException e) {
//			logger.severe(StringConstants.USER_AUTHENTICATION_FAILED_USERNAME + username + "\n" + e.getMessage());
//			e.printStackTrace();
//			StartMenu.handleSignIn();
//		}
//	}
//}
