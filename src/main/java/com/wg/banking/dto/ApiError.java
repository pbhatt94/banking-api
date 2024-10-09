package com.wg.banking.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ApiError {
    private LocalDateTime timestamp;
    private String message;
    private String details;

    public ApiError(LocalDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}