package com.wg.banking.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.wg.banking.constants.StringConstants;
import com.wg.banking.dao.ClosedAccountsDAO;
import com.wg.banking.helper.LoggingUtil;
import com.wg.banking.model.ClosedAccounts;

@Service
public class ClosedAccountsService { 

	private ClosedAccountsDAO closedAccountsDAO;
    
    private static Logger logger = LoggingUtil.getLogger(BranchService.class);

    public ClosedAccountsService(ClosedAccountsDAO closedAccountsDAO) {
        this.closedAccountsDAO = closedAccountsDAO;
    }
    
    public boolean addClosedAccounts(ClosedAccounts closedAccount)  { 
        try {
        	//checking if this account has already been closed
        	ClosedAccounts alreadyClosedAccount = closedAccountsDAO.getClosedAccount(closedAccount.getUsername());
        	
        	if(alreadyClosedAccount != null) {
        		System.out.println(StringConstants.ACCOUNT_HAS_ALREADY_BEEN_CLOSED);
        		return false;
        	}
        	
        	closedAccountsDAO.addClosedAccounts(closedAccount);
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
        return false;
    }
    

    public ClosedAccounts getClosedAccount(String username) {
        try {
			return closedAccountsDAO.getClosedAccount(username);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
        return null;
    }
    
    public List<ClosedAccounts> getAllClosedAccounts() {
    	List<ClosedAccounts> closedAccounts = new ArrayList<>();
    	try {
    		closedAccounts = closedAccountsDAO.getAllClosedAccounts();
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
    	return closedAccounts;
    }
}