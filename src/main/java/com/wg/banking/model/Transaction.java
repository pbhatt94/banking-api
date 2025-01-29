package com.wg.banking.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Transaction {
	@Id
	private String id;
	private double amount;

	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "source_account_id")
	private Account sourceAccount;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "target_account_id")
	private Account targetAccount;

	private LocalDateTime createdAt;

	@PrePersist
	protected void onCreate() {
		this.id = UUID.randomUUID().toString();
		this.createdAt = LocalDateTime.now();
	}
}
