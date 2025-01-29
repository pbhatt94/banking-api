package com.wg.banking.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wg.banking.constants.ApiMessages;
import com.wg.banking.dto.UserDto;
import com.wg.banking.model.Account;
import com.wg.banking.model.Role;
import com.wg.banking.model.User;
import com.wg.banking.service.UserService;

@SpringBootTest
public class UserControllerTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

//	@Test
//	public void testFindAllUsers() throws Exception {
//		Role role = Role.CUSTOMER;
//		Account account = null;
//		UserDto user1 = new UserDto();
//		user1.setUserId("1");
//		user1.setName("John Doe");
//		user1.setEmail("john@example.com");
//		user1.setUsername("johndoe");
//		user1.setAge(30);
//		user1.setPhoneNo("1234567890");
//		user1.setAddress("123 Main St");
//		user1.setRole(role);
//		user1.setAccount(account);
//
//		UserDto user2 = new UserDto();
//		user2.setUserId("2");
//		user2.setName("Jane Doe");
//		user2.setEmail("jane@example.com");
//		user2.setUsername("janedoe");
//		user2.setAge(28);
//		user2.setPhoneNo("0987654321");
//		user2.setAddress("456 Main St");
//		user2.setRole(role);
//		user2.setAccount(account);
//
//		when(userService.findAllUsers(0, 5)).thenReturn(Arrays.asList(user1, user2));
//		when(userService.countAllUsers()).thenReturn(2L);
//
//		mockMvc.perform(
//				get("/api/users").param("pageNumber", "0").param("limit", "5").contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk()).andExpect(jsonPath("$.status").value("SUCCESS"))
//				.andExpect(jsonPath("$.data.length()").value(2));
//	}

	@Test
	public void testFindUserById_Success() throws Exception {
		Role role = Role.CUSTOMER;
		Account account = null;
		UserDto user = new UserDto();
		user.setUserId("1");
		user.setName("John Doe");
		user.setEmail("john@example.com");
		user.setUsername("johndoe");
		user.setAge(30);
		user.setPhoneNo("1234567890");
		user.setAddress("123 Main St");
		user.setRole(role);
		user.setAccount(account);

		when(userService.findUserById("1")).thenReturn(user);

		mockMvc.perform(get("/api/user/{userId}", "1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.status").value("SUCCESS"))
				.andExpect(jsonPath("$.data.name").value("John Doe"))
				.andExpect(jsonPath("$.data.username").value("johndoe")).andExpect(jsonPath("$.data.age").value(30));
	}

	@Test
	public void testUpdateUserById() throws Exception {
		Role role = Role.CUSTOMER;
		User updatedUser = new User();
		updatedUser.setUserId("1");
		updatedUser.setName("Updated Name");
		updatedUser.setEmail("updated@example.com");
		updatedUser.setUsername("updatedUser");
		updatedUser.setAge(35);
		updatedUser.setPhoneNo("0987654321");
		updatedUser.setAddress("789 Main St");
		updatedUser.setRole(role);


		when(userService.updateUserById("1", updatedUser)).thenReturn(updatedUser);

		mockMvc.perform(put("/api/user/{userId}", "1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatedUser))).andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value("SUCCESS"))
				.andExpect(jsonPath("$.data.name").value("Updated Name"))
				.andExpect(jsonPath("$.data.username").value("updatedUser"))
				.andExpect(jsonPath("$.data.age").value(35));
	}

	@Test
	public void testDeleteUserById() throws Exception {
		when(userService.deleteUserById("1")).thenReturn(true);

		mockMvc.perform(delete("/api/user/{userId}", "1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.status").value("SUCCESS"))
				.andExpect(jsonPath("$.message").value(ApiMessages.USER_DELETED_SUCCESSFULLY));
	}

	@Test
	public void testDeleteUserByIdNotFound() throws Exception {
		when(userService.deleteUserById("1")).thenReturn(false);

		mockMvc.perform(delete("/api/user/{userId}", "1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(jsonPath("$.status").value("ERROR"))
				.andExpect(jsonPath("$.message").value(ApiMessages.FAILED_TO_DELETE_USER));
	}
}
