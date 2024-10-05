package com.wg.banking.dao;

import java.sql.SQLException;
import java.util.List;

import com.wg.banking.model.AccountDetails;

public interface AccountDetailsDAO {
	public List<AccountDetails> getAllAccountDetails() throws ClassNotFoundException, SQLException;
}
