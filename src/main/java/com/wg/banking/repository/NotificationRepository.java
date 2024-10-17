package com.wg.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wg.banking.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, String> {
	public List<Notification> findAllByReceiverUserId(String receiverId);
}