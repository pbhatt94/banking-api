package com.wg.banking.service;

import java.sql.SQLException;
import com.wg.banking.constants.StringConstants;
import com.wg.banking.dao.LoginDAO;
import com.wg.banking.helper.PasswordUtil;
import com.wg.banking.model.User;

public class LoginService {

	private LoginDAO loginDAO;

	public LoginService(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}
 
	public User authenticateUser(String username, String password) {
		User user = null;
		try {
			user = loginDAO.authenticateUser(username, password);
			if (user == null) {
				System.out.println(StringConstants.INVALID_USERNAME);
				return null;
			}

			String hashedPassword = user.getPassword();
			boolean isPasswordCorrect = PasswordUtil.checkPassword(password, hashedPassword);
			return isPasswordCorrect ? user : null;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}