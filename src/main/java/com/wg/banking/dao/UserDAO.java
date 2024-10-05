package com.wg.banking.dao;

import java.sql.SQLException;
import java.util.List;
import com.wg.banking.model.User;

public interface UserDAO {
	public User getUserById(String userId) throws ClassNotFoundException, SQLException;

	public List<User> getAllUsers() throws ClassNotFoundException, SQLException;

	public boolean addUser(User user) throws ClassNotFoundException, SQLException;

	public boolean updateUser(User user, String columnToUpdate) throws ClassNotFoundException, SQLException;

	public boolean deleteUser(String userId) throws ClassNotFoundException, SQLException;

	public List<User> getAvailableUsers() throws ClassNotFoundException, SQLException;

	public List<User> getAvailableManagers() throws ClassNotFoundException, SQLException;
}
