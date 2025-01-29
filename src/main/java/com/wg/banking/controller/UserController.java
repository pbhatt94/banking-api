package com.wg.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wg.banking.constants.ApiMessages;
import com.wg.banking.controller.criteria.UsersFilterCriteria;
import com.wg.banking.dto.ApiResponseHandler;
import com.wg.banking.dto.UpdateUserDto;
import com.wg.banking.dto.UserDto;
import com.wg.banking.mapper.UserMapper;
import com.wg.banking.model.ApiResponseStatus;
import com.wg.banking.model.User;
import com.wg.banking.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/users")
	public ResponseEntity<Object> getAllUsers(@Nullable UsersFilterCriteria criteria) {
		int page = criteria.getPage() == null ? 0 : criteria.getPage();
		int size = criteria.getSize() == null ? 10 : criteria.getSize();
		List<UserDto> users = userService.findAllUsers(UserMapper.toFilter(criteria), page, size);
		int limit = size;
		int totalCount = (int) userService.countAllUsers();
		int totalPages = (int) (totalCount + limit - 1) / limit;
		return ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.OK,
				ApiMessages.USERS_FETCHED_SUCCESSFULLY, users, page, users.size(), Integer.valueOf(totalCount),
				Integer.valueOf(totalPages));
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<Object> findUserById(@PathVariable String userId) {
		UserDto user = userService.findUserById(userId);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.OK,
				ApiMessages.USER_FOUND_SUCCESSFULLY, user);
	}

	@PutMapping("/user/{userId}")
	public ResponseEntity<Object> updateUserById(@PathVariable String userId, @Valid @RequestBody UpdateUserDto updatedUserDetails) {
		User updatedUser = userService.updateUserById(userId, updatedUserDetails);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.OK,
				ApiMessages.USER_UPDATED_SUCCESSFULLY, updatedUser);
	}
	
	@GetMapping("/user/me")
	public ResponseEntity<Object> findUserById() {
		UserDto user = UserMapper.mapUser(userService.getCurrentUser());
		return ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.OK,
				ApiMessages.USER_FOUND_SUCCESSFULLY, user);
	}

	@DeleteMapping("/user/{userId}")
	public ResponseEntity<Object> deleteUserById(@PathVariable String userId) {
		boolean isDeleted = userService.deleteUserById(userId);
		return isDeleted
				? ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.OK,
						ApiMessages.USER_DELETED_SUCCESSFULLY, null)
				: ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.NOT_FOUND,
						ApiMessages.FAILED_TO_DELETE_USER, null);
	}
}