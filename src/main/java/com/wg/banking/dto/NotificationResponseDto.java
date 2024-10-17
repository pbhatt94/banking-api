package com.wg.banking.dto;

import java.time.LocalDateTime;

import com.wg.banking.model.NotificationType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationResponseDto {
	private String id;

	private String message;

	private NotificationType type;
	
	private LocalDateTime timestamp;

	private NotificationReceiverDetails receiver;
}