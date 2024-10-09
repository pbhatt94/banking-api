package com.wg.banking.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {
	private LocalDateTime timestamp;
	private String message;
	private String details;
	private Object errors;
	private String description;

	public ApiError(LocalDateTime timestamp, String message, String details) {
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public ApiError(LocalDateTime timestamp, String message, String details, Object errors) {
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.errors = errors;
	}
	
	public ApiError(LocalDateTime timestamp, String message, String description, String details, Object errors) {
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.description = description;
		this.errors = errors;
	}
}