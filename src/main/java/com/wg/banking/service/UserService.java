package com.wg.banking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.wg.banking.model.User;

public interface UserService {
	public List<User> findAllUsers();

	public Optional<User> findUserById(String userId);

	public ResponseEntity<String> createUser(User user);

	public boolean deleteUserById(String userId);

	public Optional<User> updateUserById(String userId, User userDetails);
}
