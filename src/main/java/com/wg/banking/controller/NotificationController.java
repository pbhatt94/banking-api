package com.wg.banking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wg.banking.constants.ApiMessages;
import com.wg.banking.dto.ApiResponseHandler;
import com.wg.banking.dto.NotificationDto;
import com.wg.banking.dto.NotificationResponseDto;
import com.wg.banking.model.ApiResponseStatus;
import com.wg.banking.service.NotificationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NotificationController {

	private final NotificationService notificationService;

	@PostMapping("/notifications")
	public ResponseEntity<Object> sendNotification(@Valid @RequestBody NotificationDto notificationDto) {
		NotificationResponseDto sentNotification = notificationService.sendNotification(notificationDto);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.CREATED,
				ApiMessages.NOTIFICATION_SENT_SUCCESSFUL_MESSAGE, sentNotification);
	}

	@GetMapping("/notifications")
	public ResponseEntity<Object> getAllNotifications() {
		List<NotificationResponseDto> notifications = notificationService.getAllNotifications();
		return ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.OK,
				ApiMessages.NOTIFICATION_FETCHED_SUCCESSFUL_MESSAGE, notifications);
	}

	@GetMapping("/user/{userId}/notifications")
	public ResponseEntity<Object> getAllNotificationsByUserId(@PathVariable String userId) {
		List<NotificationResponseDto> notifications = notificationService.getAllNotificationsByUserId(userId);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.SUCCESS, HttpStatus.OK,
				ApiMessages.NOTIFICATION_FETCHED_SUCCESSFUL_MESSAGE, notifications);
	}
}
