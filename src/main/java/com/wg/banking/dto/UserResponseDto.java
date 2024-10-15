package com.wg.banking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wg.banking.model.Role;
import com.wg.banking.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDto {

	private String userId;
	private String name;
	private String email;
	private String phoneNumber;
	private String address;
	private String accountNumber;
	private Role role;

	public UserResponseDto(User user) {
		this.userId = user.getUserId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.phoneNumber = user.getPhoneNo();
		this.address = user.getAddress();
		this.role = user.getRole();
		if (user.getRole().equals(Role.CUSTOMER))
			this.accountNumber = user.getAccount().getAccountNumber();
	}
}