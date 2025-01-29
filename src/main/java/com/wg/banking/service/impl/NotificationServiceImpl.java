package com.wg.banking.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wg.banking.constants.ApiMessages;
import com.wg.banking.dto.NotificationDto;
import com.wg.banking.dto.NotificationResponseDto;
import com.wg.banking.exception.IllegalResourceException;
import com.wg.banking.exception.UserNotFoundException;
import com.wg.banking.mapper.NotificationMapper;
import com.wg.banking.model.Notification;
import com.wg.banking.model.Role;
import com.wg.banking.model.User;
import com.wg.banking.repository.NotificationRepository;
import com.wg.banking.repository.UserRepository;
import com.wg.banking.service.NotificationService;
import com.wg.banking.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

	private final NotificationRepository notificationRepository;
	private final UserRepository userRepository;
	private final UserService userService;

	@Override
	public NotificationResponseDto sendNotification(NotificationDto notificationDto) {
		Optional<User> user = userRepository.findById(notificationDto.getReceiverId());
		if (user.isEmpty()) {
			throw new UserNotFoundException(ApiMessages.USER_NOT_FOUND_ERROR);
		}
		
		User currentUser = userService.getCurrentUser();
		if(currentUser.getRole() != Role.ADMIN) {
			throw new IllegalResourceException(ApiMessages.ACTION_NOT_ALLOWED); 
		}

		Notification notification = NotificationMapper.mapDtoToEntity(notificationDto, user.get());
		Notification savedNotification = notificationRepository.save(notification);
		return NotificationMapper.mapEntityToResponseDto(savedNotification);
	}

	@Override
	public List<NotificationResponseDto> getAllNotifications() {
		List<Notification> allNotifications = notificationRepository.findAll();
		allNotifications = allNotifications.stream()
				.sorted(Comparator.comparing(Notification::getTimestamp).reversed())
				.toList();
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
		allNotifications = allNotifications.stream()
				.sorted(Comparator.comparing(Notification::getTimestamp).reversed())
				.toList();
		List<NotificationResponseDto> notifications = new ArrayList<>();
		for (Notification notification : allNotifications) {
			notifications.add(NotificationMapper.mapEntityToResponseDto(notification));
		}
		return notifications;
	}
}
