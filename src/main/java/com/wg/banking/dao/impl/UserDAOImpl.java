package com.wg.banking.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wg.banking.constants.UserConstants;
import com.wg.banking.dao.GenericDAO;
import com.wg.banking.dao.UserDAO;
import com.wg.banking.model.User;

@Repository
public class UserDAOImpl extends GenericDAO<User> implements UserDAO {

	public UserDAOImpl() {
		super();
	}

	public User getUserById(String userId) throws ClassNotFoundException, SQLException {
		String query = String.format("SELECT * FROM %s WHERE %s = '%s'", UserConstants.USER_TABLE_NAME,
				UserConstants.USER_ID_COLUMN, userId);
		return get(query);
	}

	public List<User> getAllUsers() throws ClassNotFoundException, SQLException {
		String query = String.format("SELECT * FROM %s", UserConstants.USER_TABLE_NAME);
		return getAll(query);
	}

	public boolean addUser(User user) throws ClassNotFoundException, SQLException {
		String query = String.format(
				"INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
				UserConstants.USER_TABLE_NAME, UserConstants.USER_ID_COLUMN, UserConstants.NAME_COLUMN,
				UserConstants.EMAIL_COLUMN, UserConstants.USERNAME_COLUMN, UserConstants.PASSWORD_COLUMN,
				UserConstants.AGE_COLUMN, UserConstants.GENDER_COLUMN, UserConstants.PHONE_NO_COLUMN,
				UserConstants.ADDRESS_COLUMN, UserConstants.ROLE_COLUMN, user.getUserId(), user.getName(),
				user.getEmail(), user.getUsername(), user.getPassword(), user.getAge(), user.getGender(),
				user.getPhoneNo(), user.getAddress(), user.getRole());

		return update(query);
	}

	public boolean updateUser(User user, String columnToUpdate) throws ClassNotFoundException, SQLException {
		Map<String, Object> fieldMap = new HashMap<>();
		fieldMap.put(UserConstants.NAME_COLUMN, user.getName());
		fieldMap.put(UserConstants.EMAIL_COLUMN, user.getEmail());
		fieldMap.put(UserConstants.USERNAME_COLUMN, user.getUsername());
		fieldMap.put(UserConstants.PASSWORD_COLUMN, user.getPassword());
		fieldMap.put(UserConstants.AGE_COLUMN, user.getAge());
		fieldMap.put(UserConstants.GENDER_COLUMN, user.getGender().toString());
		fieldMap.put(UserConstants.ADDRESS_COLUMN, user.getAddress());
		fieldMap.put(UserConstants.PHONE_NO_COLUMN, user.getPhoneNo());

		if (!fieldMap.containsKey(columnToUpdate)) {
			return false;
		}

		Object value = fieldMap.get(columnToUpdate);
		String sqlQuery = String.format("UPDATE %s SET %s = '%s' WHERE %s = '%s'", UserConstants.USER_TABLE_NAME,
				columnToUpdate, value, UserConstants.USER_ID_COLUMN, user.getUserId());
		return update(sqlQuery);
	}

	public boolean deleteUser(String userId) throws ClassNotFoundException, SQLException {
		String query = String.format("DELETE FROM %s WHERE %s = '%s'", UserConstants.USER_TABLE_NAME,
				UserConstants.USER_ID_COLUMN, userId);
		return update(query);
	}

	public List<User> getAvailableUsers() throws ClassNotFoundException, SQLException {
		String query = String.format("Select * from %s where %s NOT IN ( select owner_id from account) and role='%s'",
				UserConstants.USER_TABLE_NAME, UserConstants.USER_ID_COLUMN, UserConstants.CUSTOMER);

		return getAll(query);
	}

	public List<User> getAvailableManagers() throws ClassNotFoundException, SQLException {
		String query = String.format("Select * from %s where %s NOT IN ( select manager_id from branch) and role='%s'",
				UserConstants.USER_TABLE_NAME, UserConstants.USER_ID_COLUMN, UserConstants.BRANCH_MANAGER);

		return getAll(query);
	}
	
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
