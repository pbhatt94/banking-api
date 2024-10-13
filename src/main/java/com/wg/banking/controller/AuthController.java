package com.wg.banking.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wg.banking.constants.ApiMessages;
import com.wg.banking.dto.ApiResponseHandler;
import com.wg.banking.dto.JwtRequest;
import com.wg.banking.dto.UserResponseDto;
import com.wg.banking.model.ApiResponseStatus;
import com.wg.banking.model.User;
import com.wg.banking.security.JwtUtil;
import com.wg.banking.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final UserDetailsService userDetailsService;

	private final AuthenticationManager authenticationManager;

	private final PasswordEncoder passwordEncoder;

	private final JwtUtil jwtUtil;

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		String encodedPass = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPass);
		UserResponseDto userResponseDto = userService.createUser(user);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.CREATED,
				ApiMessages.USER_CREATED_SUCCESSFULLY, userResponseDto);
	}

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody JwtRequest user) {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
			UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
			String role = userDetails.getAuthorities().toString();
			role = role.substring(1, role.length() - 1);
			String jwtToken = jwtUtil.generateToken(userDetails.getUsername(), role);
			return ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.OK,
					ApiMessages.LOGGED_IN_SUCCESSFULLY, Map.of("JWT Token", jwtToken));
		} catch (Exception e) {
			return ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.UNAUTHORIZED,
					ApiMessages.INVALID_CREDENTIALS_MESSAGE, null);
		}
	}
}