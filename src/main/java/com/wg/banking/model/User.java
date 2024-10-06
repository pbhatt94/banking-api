package com.wg.banking.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

	@Id
	private String userId;
	@Column(nullable = false)
	private String name;
	@Email
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false, unique = true)
	private String username;
	@Column(nullable = false)
    @Size(min = 8, message = "Password must be at least 8 characters long")
	private String password;
	@Column(nullable = false)
	@Min(value = 18, message = "Age must be at least 18")
	private int age;
	@Column(nullable = false, unique = true)
	private String phoneNo;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private Role role;
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Account account;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	@PrePersist
	protected void onCreate() {
		this.userId = UUID.randomUUID().toString();
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
		if (this.role == null)
			role = Role.CUSTOMER;
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
}
