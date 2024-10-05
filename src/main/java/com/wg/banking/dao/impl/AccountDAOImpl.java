package com.wg.banking.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.wg.banking.constants.AccountConstants;
import com.wg.banking.dao.AccountDAO;
import com.wg.banking.dao.GenericDAO;
import com.wg.banking.model.Account;
import com.wg.banking.model.User;

public class AccountDAOImpl extends GenericDAO<Account> implements AccountDAO {

	public AccountDAOImpl() {
		super();
	}

	@Override
	protected Account mapResultSetToEntity(ResultSet resultSet) throws SQLException {
		Account account = new Account();

		account.setAccountNo(resultSet.getString(AccountConstants.ACCOUNT_NO_COLUMN));
		account.setBalance(resultSet.getDouble(AccountConstants.BALANCE_COLUMN));
		account.setOwnerId(resultSet.getString(AccountConstants.OWNER_ID_COLUMN));
		account.setBranchId(resultSet.getString(AccountConstants.BRANCH_ID_COLUMN));
		account.setCreatedAt(resultSet.getDate(AccountConstants.CREATED_AT_COLUMN));
		account.setUpdatedAt(resultSet.getDate(AccountConstants.UPDATED_AT_COLUMN));

		return account;
	}

	public Account getAccount(User user) throws ClassNotFoundException, SQLException {
		String query = "SELECT * FROM Account WHERE owner_id = \"" + user.getUserId() + "\"";
		Account account = get(query);
		return account;
	}

	public Account getAccount(Account account) throws ClassNotFoundException, SQLException {
		String query = "SELECT * FROM Account WHERE " + AccountConstants.ACCOUNT_NO_COLUMN + " = \""
				+ account.getAccountNo() + "\"";
		Account accountObj = get(query);
		return accountObj;
	}

	public Account getAccount(String accountNo) throws ClassNotFoundException, SQLException {
		String query = "SELECT * FROM Account WHERE " + AccountConstants.ACCOUNT_NO_COLUMN + " = \"" + accountNo + "\"";
		Account account = get(query);
		return account;
	}

	public List<Account> getAllAccounts() throws ClassNotFoundException, SQLException {
		String query = String.format("SELECT * FROM %s", AccountConstants.ACCOUNT_TABLE_NAME);
		return getAll(query);
	}

	public List<Account> getAllAccounts(String branchId) throws ClassNotFoundException, SQLException {
		String query = String.format("SELECT * FROM %s WHERE %s = '%s'", AccountConstants.ACCOUNT_TABLE_NAME,
				AccountConstants.BRANCH_ID_COLUMN, branchId);
		return getAll(query);
	}

	public boolean addAccount(Account account) throws ClassNotFoundException, SQLException {
		String query = String.format(
				"INSERT INTO %s (%s, %s, %s, %s, %s, %s) VALUES ('%s', %.2f, '%s', '%s', '%s', '%s')",
				AccountConstants.ACCOUNT_TABLE_NAME, AccountConstants.ACCOUNT_NO_COLUMN,
				AccountConstants.BALANCE_COLUMN, AccountConstants.OWNER_ID_COLUMN, AccountConstants.BRANCH_ID_COLUMN,
				AccountConstants.CREATED_AT_COLUMN, AccountConstants.UPDATED_AT_COLUMN, account.getAccountNo(),
				account.getBalance(), account.getOwnerId(), account.getBranchId(),
				new java.sql.Date(account.getCreatedAt().getTime()),
				new java.sql.Date(account.getUpdatedAt().getTime()));

		return update(query);
	}

	public boolean updateAccount(Account account) throws ClassNotFoundException, SQLException {
		String query = String.format("UPDATE %s SET %s = %.2f, %s = '%s', %s = '%s', %s = '%s' WHERE %s = '%s'",
				AccountConstants.ACCOUNT_TABLE_NAME, AccountConstants.BALANCE_COLUMN, account.getBalance(),
				AccountConstants.OWNER_ID_COLUMN, account.getOwnerId(), AccountConstants.BRANCH_ID_COLUMN,
				account.getBranchId(), AccountConstants.UPDATED_AT_COLUMN,
				new java.sql.Date(account.getUpdatedAt().getTime()), AccountConstants.ACCOUNT_NO_COLUMN,
				account.getAccountNo());
		return update(query);
	}

	public boolean deleteAccount(String branchId) throws ClassNotFoundException, SQLException {
		String query = String.format("DELETE FROM %s WHERE %s = '%s'", AccountConstants.ACCOUNT_TABLE_NAME,
				AccountConstants.BRANCH_ID_COLUMN, branchId);
		return update(query);
	}

	public Account getUser(String accountNumber) throws ClassNotFoundException, SQLException {
		String query = String.format("SELECT * FROM %s WHERE %s = '%s'", AccountConstants.ACCOUNT_TABLE_NAME,
				AccountConstants.ACCOUNT_NO_COLUMN, accountNumber);
		return get(query);
	}
}
