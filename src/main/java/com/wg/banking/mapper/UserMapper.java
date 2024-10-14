package com.wg.banking.mapper;

import com.wg.banking.dto.UserDto;
import com.wg.banking.model.User;

public class UserMapper {
	public static UserDto mapUser(User user) {
		UserDto userDto = new UserDto();
		userDto.setUserId(user.getUserId());
		userDto.setName(user.getName());
		userDto.setAge(user.getAge());
		userDto.setAddress(user.getAddress());
		userDto.setEmail(user.getEmail());
		userDto.setUsername(user.getUsername());
		userDto.setPhoneNo(user.getPhoneNo());
		userDto.setRole(user.getRole());
		userDto.setAccount(user.getAccount());
		return userDto;
	}
}