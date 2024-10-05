package com.wg.banking.model;

import java.util.Date;
import java.util.Objects;

public class Notification {
    private String notificationId;           
    private String message;
    private NotificationType notificationType; 
    private String receiverId;
    private Date createdAt;

    public enum NotificationType {
        ACCOUNT_ACTIVITY,
        SYSTEM_ALERT
    }

    public Notification() {}

    public Notification(String notificationId, String message, NotificationType notificationType, 
                        String receiverId, Date createdAt) {
        this.notificationId = notificationId;
        this.message = message;
        this.notificationType = notificationType;
        this.receiverId = receiverId;
        this.createdAt = createdAt;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notificationId='" + notificationId + '\'' +
                ", message='" + message + '\'' +
                ", notificationType=" + notificationType +
                ", receiverId='" + receiverId + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(createdAt, message, notificationId, notificationType, receiverId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notification other = (Notification) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(message, other.message)
				&& Objects.equals(notificationId, other.notificationId) && notificationType == other.notificationType
				&& Objects.equals(receiverId, other.receiverId);
	}
    
    
}
