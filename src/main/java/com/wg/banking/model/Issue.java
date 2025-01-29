package com.wg.banking.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Issue {
	@Id
	private String id;

	@NotNull
	@NotEmpty
	private String title;

	@NotNull
	@NotEmpty
	private String description;

	private IssueStatus status;
	private IssuePriority priority;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@NotNull
	private LocalDateTime createdAt;
	@NotNull
	private LocalDateTime updatedAt;

	@PrePersist
	protected void onCreate() {
		this.id = UUID.randomUUID().toString();
		this.status = IssueStatus.PENDING;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
}