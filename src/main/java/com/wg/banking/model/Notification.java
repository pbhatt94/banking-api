package com.wg.banking.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Data
@Entity
public class Notification {

	@Id
	private String id;

	private String message;

	private NotificationType type;

	private LocalDateTime timestamp;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User receiver;
	
	@PrePersist
	public void onCreate() {
		this.id = UUID.randomUUID().toString();
		this.timestamp = LocalDateTime.now(); 
	}
}