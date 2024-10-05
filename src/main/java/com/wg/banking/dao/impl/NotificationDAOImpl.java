package com.wg.banking.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.wg.banking.constants.NotificationConstants;
import com.wg.banking.dao.GenericDAO;
import com.wg.banking.model.Notification;
import com.wg.banking.dao.NotificationDAO;

public class NotificationDAOImpl extends GenericDAO<Notification> implements NotificationDAO {

	public NotificationDAOImpl() {
		super();
	}

	@Override
	protected Notification mapResultSetToEntity(ResultSet resultSet) throws SQLException {
		Notification notification = new Notification();

		notification.setNotificationId(resultSet.getString(NotificationConstants.NOTIFICATION_ID_COLUMN));
		notification.setMessage(resultSet.getString(NotificationConstants.MESSAGE_COLUMN));
		String transactionTypeString = resultSet.getString(NotificationConstants.NOTIFICATION_TYPE_COLUMN);
		notification.setNotificationType(Notification.NotificationType.valueOf(transactionTypeString));
		notification.setReceiverId(resultSet.getString(NotificationConstants.RECEIVER_ID_COLUMN));
		notification.setCreatedAt(resultSet.getDate(NotificationConstants.CREATED_AT_COLUMN));

		return notification;
	}

	public List<Notification> getAllNotifications() throws ClassNotFoundException, SQLException {
		String query = "SELECT * FROM " + NotificationConstants.NOTIFICATION_TABLE_NAME;
		return getAll(query);
	}

	public List<Notification> getAllNotifications(String userId) throws ClassNotFoundException, SQLException {
		String query = String.format("SELECT * FROM %s WHERE %s = '%s'", NotificationConstants.NOTIFICATION_TABLE_NAME,
				NotificationConstants.RECEIVER_ID_COLUMN, userId);
		return getAll(query);
	}

	public Notification getNotificationById(String notificationId) throws ClassNotFoundException, SQLException {
		String query = String.format("SELECT * FROM %s WHERE %s = '%s'", NotificationConstants.NOTIFICATION_TABLE_NAME,
				NotificationConstants.NOTIFICATION_ID_COLUMN, notificationId);
		return get(query);
	}

	public boolean addNotification(Notification notification) throws ClassNotFoundException, SQLException {
		String query = String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES ('%s', '%s', '%s', '%s')",
				NotificationConstants.NOTIFICATION_TABLE_NAME, NotificationConstants.NOTIFICATION_ID_COLUMN,
				NotificationConstants.MESSAGE_COLUMN, NotificationConstants.NOTIFICATION_TYPE_COLUMN,
				NotificationConstants.RECEIVER_ID_COLUMN, notification.getNotificationId(), notification.getMessage(),
				notification.getNotificationType(), notification.getReceiverId());
		return update(query);
	}
}
