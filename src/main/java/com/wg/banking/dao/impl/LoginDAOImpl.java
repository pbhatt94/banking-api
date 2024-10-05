package com.wg.banking.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.wg.banking.constants.UserConstants;
import com.wg.banking.dao.GenericDAO;
import com.wg.banking.dao.LoginDAO;
import com.wg.banking.model.User;

public class LoginDAOImpl extends GenericDAO<User> implements LoginDAO {
	public User authenticateUser(String username, String password) throws ClassNotFoundException, SQLException {
		String sqlQuery = "SELECT * FROM User WHERE username=" + "\"" + username + "\"";
		return get(sqlQuery);
	}

	@Override
	protected User mapResultSetToEntity(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setUserId(resultSet.getString(UserConstants.USER_ID_COLUMN));
		user.setName(resultSet.getString(UserConstants.NAME_COLUMN));
		user.setEmail(resultSet.getString(UserConstants.EMAIL_COLUMN));
		user.setUsername(resultSet.getString(UserConstants.USERNAME_COLUMN));
		user.setPassword(resultSet.getString(UserConstants.PASSWORD_COLUMN));
		user.setAge(resultSet.getInt(UserConstants.AGE_COLUMN));
		user.setGender(User.Gender.valueOf(resultSet.getString(UserConstants.GENDER_COLUMN)));
		user.setPhoneNo(resultSet.getString(UserConstants.PHONE_NO_COLUMN));
		user.setAddress(resultSet.getString(UserConstants.ADDRESS_COLUMN));
		user.setRole(User.Role.valueOf(resultSet.getString(UserConstants.ROLE_COLUMN)));
		user.setCreatedAt(resultSet.getDate(UserConstants.CREATED_AT_COLUMN));
		user.setUpdatedAt(resultSet.getDate(UserConstants.UPDATED_AT_COLUMN));
		return user;
	}
}