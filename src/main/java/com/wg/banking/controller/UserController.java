package com.wg.banking.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}

	@GetMapping
	public List<User> findAllUsers() {
		return userService.findAllUsers();
	}

	@GetMapping("/{userId}")
	public Optional<User> findUserById(@PathVariable String userId) {
		return userService.findUserById(userId);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUserById(@PathVariable String userId, @RequestBody User userDetails) {
		Optional<User> updatedUser = userService.updateUserById(userId, userDetails);

		if (updatedUser.isPresent()) {
			return ResponseEntity.ok(updatedUser.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUserById(@PathVariable String userId) {
		boolean isDeleted = userService.deleteUserById(userId);

		if (isDeleted) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}