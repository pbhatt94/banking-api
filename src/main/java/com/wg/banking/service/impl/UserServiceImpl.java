package com.wg.banking.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wg.banking.helper.JsonUtil;
import com.wg.banking.model.Account;
import com.wg.banking.model.User;
import com.wg.banking.model.UserResponse;
import com.wg.banking.repository.UserRepository;
import com.wg.banking.service.AccountService;
import com.wg.banking.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final AccountService accountService;
	private final PasswordEncoder passwordEncoder;

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public ResponseEntity<String> createUser(User user) {
		User savedUser = userRepository.save(user);
		Account account = accountService.createAccount(savedUser);
		savedUser.setAccount(account);
		savedUser = userRepository.save(savedUser);
		return ResponseEntity.ok(JsonUtil.toJson(new UserResponse(savedUser)));
	}

	@Override
	public Optional<User> findUserById(String userId) {
		return userRepository.findById(userId);
	}

	@Override
	public boolean deleteUserById(String userId) {
		if (!userRepository.existsById(userId))
			return false;
		userRepository.deleteById(userId);
		return true;
	}

	@Override
	public Optional<User> updateUserById(String userId, User userDetails) {
		Optional<User> existingUser = userRepository.findById(userId);
		if (existingUser.isEmpty()) {
			return Optional.empty();
		}
		User user = existingUser.get();
		mapUpdatedUser(userId, userDetails, user);
		return Optional.of(userRepository.save(user));
	}

	private void mapUpdatedUser(String userId, User userDetails, User user) {
		user.setUserId(userId);
		user.setAge(userDetails.getAge());
		user.setEmail(userDetails.getEmail());
		user.setName(userDetails.getName());
		user.setAddress(userDetails.getAddress());
		user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
		user.setPhoneNo(userDetails.getPhoneNo());
		user.setUpdatedAt(LocalDateTime.now());
		user.setUsername(userDetails.getUsername());
	}

}