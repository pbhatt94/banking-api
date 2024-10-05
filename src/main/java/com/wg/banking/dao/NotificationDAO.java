package com.wg.banking.dao;

import java.sql.SQLException;
import java.util.List;

import com.wg.banking.model.Notification;

public interface NotificationDAO {
	public List<Notification> getAllNotifications() throws ClassNotFoundException, SQLException;

	public List<Notification> getAllNotifications(String userId) throws ClassNotFoundException, SQLException;

	public Notification getNotificationById(String notificationId) throws ClassNotFoundException, SQLException;

	public boolean addNotification(Notification notification) throws ClassNotFoundException, SQLException;
}