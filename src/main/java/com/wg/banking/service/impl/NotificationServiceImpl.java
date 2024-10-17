package com.wg.banking.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wg.banking.constants.ApiMessages;
import com.wg.banking.dto.NotificationDto;
import com.wg.banking.dto.NotificationResponseDto;
import com.wg.banking.exception.UserNotFoundException;
import com.wg.banking.mapper.NotificationMapper;
import com.wg.banking.model.Notification;
import com.wg.banking.model.User;
import com.wg.banking.repository.NotificationRepository;
import com.wg.banking.repository.UserRepository;
import com.wg.banking.service.NotificationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

	private final NotificationRepository notificationRepository;
	private final UserRepository userRepository;

	@Override
	public NotificationResponseDto sendNotification(NotificationDto notificationDto) {
		Optional<User> user = userRepository.findById(notificationDto.getReceiverId());
		if (user.isEmpty()) {
			throw new UserNotFoundException(ApiMessages.USER_NOT_FOUND_ERROR);
		}

		Notification notification = NotificationMapper.mapDtoToEntity(notificationDto, user.get());
		Notification savedNotification = notificationRepository.save(notification);
		return NotificationMapper.mapEntityToResponseDto(savedNotification);
	}

	@Override
	public List<NotificationResponseDto> getAllNotifications() {
		List<Notification> allNotifications = notificationRepository.findAll();
		List<NotificationResponseDto> notifications = new ArrayList<>();
		for (Notification notification : allNotifications) {
			notifications.add(NotificationMapper.mapEntityToResponseDto(notification));
		}
		return notifications;
	}

	@Override
	public List<NotificationResponseDto> getAllNotificationsByUserId(String userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isEmpty()) {
			throw new UserNotFoundException(ApiMessages.USER_NOT_FOUND_ERROR);
		}

		List<Notification> allNotifications = notificationRepository.findAllByReceiverUserId(userId);
		List<NotificationResponseDto> notifications = new ArrayList<>();
		for (Notification notification : allNotifications) {
			notifications.add(NotificationMapper.mapEntityToResponseDto(notification));
		}
		return notifications;
	}

}
