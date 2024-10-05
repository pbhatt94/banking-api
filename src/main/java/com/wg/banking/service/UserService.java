package com.wg.banking.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wg.banking.dao.UserDAO;
import com.wg.banking.dao.impl.ClosedAccountsDAOImpl;
import com.wg.banking.constants.StringConstants;
import com.wg.banking.constants.UserConstants;
import com.wg.banking.dao.ClosedAccountsDAO;
import com.wg.banking.helper.LoggingUtil;
import com.wg.banking.model.ClosedAccounts;
import com.wg.banking.model.User;

@Service
public class UserService {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private ClosedAccountsService closedAccountsService;

	private static Logger logger = LoggingUtil.getLogger(UserService.class);

//	public UserService(UserDAO userDAO) {
//		this.userDAO = userDAO;
//		closedAccountsDAO = new ClosedAccountsDAOImpl();
//		closedAccountsService = new ClosedAccountsService(closedAccountsDAO);
//	} 

	@Autowired
	public UserService(UserDAO userDAO, ClosedAccountsService closedAccountsService) {
		this.userDAO = userDAO;
		this.closedAccountsService = closedAccountsService;
	}

	public User getUserById(String userId) {
		try {
			return userDAO.getUserById(userId);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public List<User> getAllActiveUsers() {
		try {
			List<User> allUsers = userDAO.getAllUsers();
			List<ClosedAccounts> inactiveUsers = closedAccountsService.getAllClosedAccounts();
			Set<String> inactiveUsersSet = inactiveUsers.stream().map(closedAccount -> closedAccount.getUsername())
					.collect(Collectors.toSet());

			List<User> activeUsers = allUsers.stream().filter(user -> !(inactiveUsersSet.contains(user.getUsername())))
					.collect(Collectors.toList());
			return activeUsers;
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public boolean addUser(User user) {
		try {
			if (user.getRole().toString().equals(UserConstants.ADMIN)) {
				System.out.println(StringConstants.ADMIN_ALREADY_EXISTS);
				return false;
			}
			return userDAO.addUser(user);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public List<User> getAllInactiveUsers() {
		try {
			List<User> allUsers = userDAO.getAllUsers();
			List<ClosedAccounts> inactiveUsers = closedAccountsService.getAllClosedAccounts();
			Set<String> inactiveUsersSet = inactiveUsers.stream().map(closedAccount -> closedAccount.getUsername())
					.collect(Collectors.toSet());

			return allUsers.stream().filter(user -> (inactiveUsersSet.contains(user.getUsername())))
					.collect(Collectors.toList());
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateUser(User user, String columnToUpdate) {
		try {
			if (columnToUpdate.equalsIgnoreCase(UserConstants.ROLE_COLUMN)) {
				System.out.println(StringConstants.ROLE_CANNOT_BE_UPDATED);
				return false;
			}
			return userDAO.updateUser(user, columnToUpdate);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteUser(String userId) {
		try {
			User user = userDAO.getUserById(userId);

			if (user == null) {
				System.out.println(StringConstants.USER_NOT_FOUND);
				return false;
			}
			if (user.getRole().toString().equalsIgnoreCase(UserConstants.ADMIN)) {
				System.out.println(StringConstants.ADMIN_CANNOT_BE_DELETED);
				return false;
			}

			return userDAO.deleteUser(userId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<User> getAvailableUsers() {
		List<User> users = new ArrayList<>();
		try {
			users = userDAO.getAvailableUsers();
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return users;
	}

	public List<User> getAvailableManagers() {
		List<User> managers = new ArrayList<>();
		try {
			managers = userDAO.getAvailableManagers();
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return managers;
	}
}