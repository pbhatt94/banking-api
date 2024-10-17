package com.wg.banking.mapper;

import java.time.LocalDateTime;
import java.util.UUID;

import com.wg.banking.dto.NotificationDto;
import com.wg.banking.dto.NotificationReceiverDetails;
import com.wg.banking.dto.NotificationResponseDto;
import com.wg.banking.model.Notification;
import com.wg.banking.model.User;

public class NotificationMapper {

	public static Notification mapDtoToEntity(NotificationDto notificationDto, User user) {
		Notification notification = new Notification();
		notification.setId(UUID.randomUUID().toString());
		notification.setMessage(notificationDto.getMessage());
		notification.setReceiver(user);
		notification.setType(notificationDto.getType());
		notification.setTimestamp(LocalDateTime.now());
		return notification;
	}

	public static NotificationDto mapEntityToDto(Notification notification) {
		NotificationDto notificationDto = new NotificationDto();
		notificationDto.setMessage(notification.getMessage());
		notificationDto.setReceiverId(notification.getReceiver().getUserId());
		notificationDto.setType(notification.getType());
		return notificationDto;
	}

	public static NotificationResponseDto mapEntityToResponseDto(Notification notification) {
		NotificationReceiverDetails receiver = NotificationReceiverDetails.builder()
				.id(notification.getReceiver().getUserId()).email(notification.getReceiver().getEmail())
				.name(notification.getReceiver().getName()).username(notification.getReceiver().getUsername()).build();

		NotificationResponseDto notificationResponse = NotificationResponseDto.builder().id(notification.getId())
				.message(notification.getMessage()).type(notification.getType()).timestamp(notification.getTimestamp())
				.receiver(receiver).build();
		
		return notificationResponse;
	}
}
