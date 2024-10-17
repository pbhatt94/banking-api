package com.wg.banking.dto;

import com.wg.banking.model.NotificationType;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NotificationDto {
	
	@NotBlank
	private String message;
	
	private NotificationType type;
	
	@NotBlank
	private String receiverId;
}