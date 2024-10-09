package com.wg.banking.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wg.banking.constants.ApiMessages;
import com.wg.banking.dto.ApiResponseHandler;
import com.wg.banking.model.ApiResponseStatus;
import com.wg.banking.model.User;
import com.wg.banking.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<Object> findAllUsers() {
		List<User> users = userService.findAllUsers();
		return ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.OK, ApiMessages.USERS_FETCHED_SUCCESSFULLY, users);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<Object> findUserById(@PathVariable String userId) {
		User user = userService.findUserById(userId);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.OK, ApiMessages.USER_FOUND_SUCCESSFULLY, user);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<Object> updateUserById(@PathVariable String userId, @RequestBody User updatedUserDetails) {
		User updatedUser = userService.updateUserById(userId, updatedUserDetails);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.OK, ApiMessages.USER_UPDATED_SUCCESSFULLY, updatedUser);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Object> deleteUserById(@PathVariable String userId) {
		boolean isDeleted = userService.deleteUserById(userId);
		return isDeleted ? ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.OK, ApiMessages.USER_DELETED_SUCCESSFULLY, null) : 
			ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.NOT_FOUND, ApiMessages.FAILED_TO_DELETE_USER, null);
	}
}