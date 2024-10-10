package com.wg.banking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wg.banking.model.ApiResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
	@JsonProperty("status")
	private ApiResponseStatus status;

	@JsonProperty("message")
	private String message;

	@JsonProperty("data")
	private Object data;

	@JsonProperty("error")
	private Object error;

	@JsonProperty("page")
	private Integer page;

	@JsonProperty("size")
	private Integer size;

	@JsonProperty("totalItems")
	private Integer totalItems;

	@JsonProperty("totalPages")
	private Integer totalPages;

	public ApiResponse(ApiResponseStatus status, String message, Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public ApiResponse(ApiResponseStatus status, String message, Object data, Object error) {
		this.status = status;
		this.message = message;
		this.data = data;
		this.error = error;
	}

	public ApiResponse(ApiResponseStatus status, String message, Object data, Integer page, Integer size,
			Integer totalItems, Integer totalPages) {
		this.status = status;
		this.message = message;
		this.data = data;
		this.page = page;
		this.size = size;
		this.totalItems = totalItems;
		this.totalPages = totalPages;
	}
}