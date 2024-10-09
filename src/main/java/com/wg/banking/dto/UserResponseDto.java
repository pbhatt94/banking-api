package com.wg.banking.dto;

import com.wg.banking.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

	private String name;
	private String email;
	private String phoneNumber;
	private String address;
	private String accountNumber;

	public UserResponseDto(User user) {
		this.name = user.getName();
		this.email = user.getEmail();
		this.phoneNumber = user.getPhoneNo();
		this.address = user.getAddress();
		this.accountNumber = user.getAccount().getAccountNumber();
	}

}