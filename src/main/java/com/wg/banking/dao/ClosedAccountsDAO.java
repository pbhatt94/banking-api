package com.wg.banking.dao;

import java.sql.SQLException;
import java.util.List;

import com.wg.banking.model.ClosedAccounts;

public interface ClosedAccountsDAO {
	public ClosedAccounts getClosedAccount(String username) throws ClassNotFoundException, SQLException;

	public List<ClosedAccounts> getAllClosedAccounts() throws ClassNotFoundException, SQLException;

	public boolean addClosedAccounts(ClosedAccounts closedAccounts) throws ClassNotFoundException, SQLException;
}
