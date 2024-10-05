package com.wg.banking.controller;

import com.wg.banking.constants.StringConstants;
import com.wg.banking.model.ClosedAccounts;
import com.wg.banking.service.ClosedAccountsService;


public class ClosedAccountsController {
    private ClosedAccountsService closedAccountsService;
    
    public ClosedAccountsController(ClosedAccountsService closedAccountsService) {
        this.closedAccountsService = closedAccountsService;
    }

    public boolean addClosedAccounts(ClosedAccounts closedAccount) {
    	return closedAccountsService.addClosedAccounts(closedAccount);
    }

    public ClosedAccounts getClosedAccount(String username) {
    	ClosedAccounts closedAccount = null;
    	try {
            closedAccount = closedAccountsService.getClosedAccount(username);
        } catch (Exception e) {
            System.out.println(StringConstants.ERROR_RETRIEVING_ACCOUNT + e.getMessage());
        }
    	return closedAccount;
    }    
}
