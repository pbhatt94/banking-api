package com.wg.banking.dto;

import lombok.Data;

@Data
public class TransactionDTO {
	private double amount;
	private String targetAccountNumber;
}