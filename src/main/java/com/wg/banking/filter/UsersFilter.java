package com.wg.banking.filter;

import com.wg.banking.model.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsersFilter {
	private String name;
	private String username;
	private Integer age;
	private String address;
	private Role role;
}