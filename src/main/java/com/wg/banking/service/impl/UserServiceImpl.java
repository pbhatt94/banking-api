package com.wg.banking.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wg.banking.constants.ApiMessages;
import com.wg.banking.dto.UserDto;
import com.wg.banking.dto.UserResponseDto;
import com.wg.banking.exception.UserNotFoundException;
import com.wg.banking.mapper.UserMapper;
import com.wg.banking.model.Account;
import com.wg.banking.model.Role;
import com.wg.banking.model.User;
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
	public List<UserDto> findAllUsers(Integer pageNumber, Integer pageSize) {
		if (pageNumber <= 0 || pageSize <= 0)
			throw new InvalidInputException(ApiMessages.INVALID_PAGE_NUMBER_OR_LIMIT);
		pageNumber--;
		pageNumber = Math.max(0, pageNumber);
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<User> page = userRepository.findAll(pageable);
		List<User> users = page.getContent();
		List<UserDto> userDtos = new ArrayList<>();
		for (User user : users) {
			userDtos.add(UserMapper.mapUser(user));
		}
		return userDtos;
	}

	@Override
	public UserResponseDto createUser(User user) {
		User savedUser = userRepository.save(user);
		if (isCustomer(savedUser)) {
			Account account = accountService.createAccount(savedUser);
			savedUser.setAccount(account);
			savedUser = userRepository.save(savedUser);
		}
		UserResponseDto userResponse = new UserResponseDto(savedUser);
		return userResponse;
	}

	@Override
	public UserDto findUserById(String userId) {
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent())
			throw new UserNotFoundException(ApiMessages.USER_NOT_FOUND_ERROR + ": " + userId);
		return UserMapper.mapUser(user.get());
	}

	@Override
	public boolean deleteUserById(String userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isEmpty())
			return false;
		else if (isAdmin(user))
			return false;
		userRepository.deleteById(userId);
		return true;
	}

	@Override
	public User updateUserById(String userId, User userDetails) {
		Optional<User> existingUser = userRepository.findById(userId);
		if (existingUser.isEmpty()) {
			throw new UserNotFoundException(ApiMessages.USER_NOT_FOUND_ERROR + ": " + userId);
		}
		User user = existingUser.get();
		mapUpdatedUser(userId, userDetails, user);
		return userRepository.save(user);
	}

	@Override
	public long countAllUsers() {
		long count = userRepository.count();
		return count;
	}

	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.isAuthenticated()) {
			String username = authentication.getName();
			return userRepository.findByUsername(username)
					.orElseThrow(() -> new UserNotFoundException(ApiMessages.USER_NOT_FOUND_ERROR + ": " + username));
		}

		return null;
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

	private boolean isAdmin(Optional<User> user) {
		return user.get().getRole().equals(Role.ADMIN);
	}

	private boolean isCustomer(User user) {
		return user.getRole().equals(Role.CUSTOMER);
	}
}