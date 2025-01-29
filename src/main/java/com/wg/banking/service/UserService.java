package com.wg.banking.service;

import java.util.List;

import com.wg.banking.dto.UpdateUserDto;
import com.wg.banking.dto.UserDto;
import com.wg.banking.filter.UsersFilter;
import com.wg.banking.model.User;

public interface UserService {
	public List<UserDto> findAllUsers(UsersFilter filter,Integer pageNumber, Integer pageSize);

	public UserDto findUserById(String userId);
	
	public User findByUsername(String username);

	public UserDto createUser(User user);

	public boolean deleteUserById(String userId);

	public User updateUserById(String userId, UpdateUserDto userDetails);
	
	public long countAllUsers();

	public User getCurrentUser();
}