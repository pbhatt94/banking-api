package com.wg.banking.service;

import java.util.List;

import com.wg.banking.dto.NotificationDto;
import com.wg.banking.dto.NotificationResponseDto;

public interface NotificationService {
	public NotificationResponseDto sendNotification(NotificationDto notificationDto);
	
	public List<NotificationResponseDto> getAllNotifications();
	
	public List<NotificationResponseDto> getAllNotificationsByUserId(String userId);
}
