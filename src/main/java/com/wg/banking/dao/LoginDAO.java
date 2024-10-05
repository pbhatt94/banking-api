package com.wg.banking.dao;

import java.sql.SQLException;

import com.wg.banking.model.User;

public interface LoginDAO {
	public User authenticateUser(String username, String password) throws ClassNotFoundException, SQLException;
}
