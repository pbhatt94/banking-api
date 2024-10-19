package com.wg.banking.controller.criteria;

import com.wg.banking.model.Role;

import lombok.Data;

@Data
public class UsersFilterCriteria {
	
	private String name;
	private Role role;
	private Integer age;
	private String username;
	private String address;
	private int page;
	private int size;
}