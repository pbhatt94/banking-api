package com.wg.banking.dao;

import java.sql.SQLException;
import java.util.List;

import com.wg.banking.model.Account;
import com.wg.banking.model.User;

public interface AccountDAO {
	public Account getAccount(User user) throws ClassNotFoundException, SQLException;

	public Account getAccount(Account account) throws ClassNotFoundException, SQLException;

	public Account getAccount(String accountNo) throws ClassNotFoundException, SQLException;

	public List<Account> getAllAccounts() throws ClassNotFoundException, SQLException;

	public List<Account> getAllAccounts(String branchId) throws ClassNotFoundException, SQLException;

	public boolean addAccount(Account account) throws ClassNotFoundException, SQLException;

	public boolean updateAccount(Account account) throws ClassNotFoundException, SQLException;

	public boolean deleteAccount(String branchId) throws ClassNotFoundException, SQLException;

	public Account getUser(String accountNumber) throws ClassNotFoundException, SQLException;
}