package com.wg.banking.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wg.banking.constants.ClosedAccountsConstants;
import com.wg.banking.dao.ClosedAccountsDAO;
import com.wg.banking.dao.GenericDAO;
import com.wg.banking.model.ClosedAccounts;

@Repository
public class ClosedAccountsDAOImpl extends GenericDAO<ClosedAccounts> implements ClosedAccountsDAO {

	public ClosedAccountsDAOImpl() {
		super();
	}

	@Override
	protected ClosedAccounts mapResultSetToEntity(ResultSet resultSet) throws SQLException {
		ClosedAccounts closedAccounts = new ClosedAccounts();
		closedAccounts.setId(resultSet.getString(ClosedAccountsConstants.COLUMN_ID));
		closedAccounts.setUsername(resultSet.getString(ClosedAccountsConstants.COLUMN_USERNAME));
		closedAccounts.setClosedAt(resultSet.getTimestamp(ClosedAccountsConstants.COLUMN_CLOSED_AT));
		return closedAccounts;
	}

	public ClosedAccounts getClosedAccount(String username) throws ClassNotFoundException, SQLException {
		String query = String.format("SELECT * FROM %s WHERE %s = '%s'", ClosedAccountsConstants.TABLE_NAME,
				ClosedAccountsConstants.COLUMN_USERNAME, username);
		return get(query);
	}

	public List<ClosedAccounts> getAllClosedAccounts() throws ClassNotFoundException, SQLException {
		String query = String.format("SELECT * FROM %s", ClosedAccountsConstants.TABLE_NAME);
		return getAll(query);
	}

	public boolean addClosedAccounts(ClosedAccounts closedAccounts) throws ClassNotFoundException, SQLException {
		String query = String.format("INSERT INTO %s (%s, %s) " + "VALUES ('%s', '%s')",
				ClosedAccountsConstants.TABLE_NAME, ClosedAccountsConstants.COLUMN_ID,
				ClosedAccountsConstants.COLUMN_USERNAME, closedAccounts.getId(), closedAccounts.getUsername());

		return update(query);
	}

}
