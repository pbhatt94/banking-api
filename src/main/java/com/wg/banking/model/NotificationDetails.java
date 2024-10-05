package com.wg.banking.model;

public class NotificationDetails {
	
	Notification notification;
	User user;
	
	public NotificationDetails() {}
	
	public NotificationDetails(Notification notification, User user) {
		super();
		this.notification = notification;
		this.user = user;
	}
	
	public Notification getNotification() {
		return notification;
	}
	
	public void setNotification(Notification notification) {
		this.notification = notification;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "NotificationDetails [notification=" + notification + ", user=" + user + "]";
	}	
}
