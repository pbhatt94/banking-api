package com.wg.banking.service;

import java.util.List;
import com.wg.banking.dto.UserResponseDto;
import com.wg.banking.model.User;

public interface UserService {
	public List<User> findAllUsers();

	public User findUserById(String userId);

	public UserResponseDto createUser(User user);

	public boolean deleteUserById(String userId);

	public User updateUserById(String userId, User userDetails);

	public User getCurrentUser();
}
