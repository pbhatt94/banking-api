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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wg.banking.constants.ApiMessages;
import com.wg.banking.dto.ApiResponseHandler;
import com.wg.banking.model.ApiResponseStatus;
import com.wg.banking.model.User;
import com.wg.banking.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
//	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> findAllUsers(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "limit", defaultValue = "5", required = false) Integer limit) {
		List<User> users = userService.findAllUsers(pageNumber, limit);
		int totalCount = (int) userService.countAllUsers();
		int totalPages = (int) (totalCount + limit - 1) / limit;
		return ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.OK,
				ApiMessages.USERS_FETCHED_SUCCESSFULLY, users, pageNumber, limit, Integer.valueOf(totalCount),
				Integer.valueOf(totalPages));
	}

	@GetMapping("/user/{userId}")
//	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> findUserById(@PathVariable String userId) {
		User user = userService.findUserById(userId);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.OK,
				ApiMessages.USER_FOUND_SUCCESSFULLY, user);
	}

	@PutMapping("/user/{userId}")
	public ResponseEntity<Object> updateUserById(@PathVariable String userId, @RequestBody User updatedUserDetails) {
		User updatedUser = userService.updateUserById(userId, updatedUserDetails);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.OK,
				ApiMessages.USER_UPDATED_SUCCESSFULLY, updatedUser);
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