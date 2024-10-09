package com.wg.banking.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.wg.banking.model.ApiResponseStatus;

public class ApiResponseHandler {
	public static ResponseEntity<Object> buildResponse(ApiResponseStatus status, HttpStatus statusCode, String message,
			Object data) {
		ApiResponse apiResponse = new ApiResponse(status, message, data);
		return new ResponseEntity<>(apiResponse, statusCode);
	}
}