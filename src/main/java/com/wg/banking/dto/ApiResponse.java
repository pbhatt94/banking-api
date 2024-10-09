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
}
