package com.wg.banking.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.wg.banking.dto.TransactionDTO;
import com.wg.banking.exception.CustomerNotFoundException;
import com.wg.banking.exception.GlobalExceptionHandler;
import com.wg.banking.model.Account;
import com.wg.banking.model.Role;
import com.wg.banking.model.User;
import com.wg.banking.service.AccountService;
import com.wg.banking.service.UserService;

@SpringBootTest
public class AccountControllerTest {

	@Mock
	private AccountService accountService;

	@Mock
	private UserService userService;

	@InjectMocks
	private AccountController accountController;

	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(accountController).setControllerAdvice(new GlobalExceptionHandler())
				.build();
	}

	@Test
	public void testDeposit_Success() throws Exception {
		String accountId = "12345";
		TransactionDTO transaction = new TransactionDTO();
		// Set transaction details if needed

		Account account = new Account();
		account.setId("db37bf87-63a8-400d-8fda-4638a5fb3635");
		account.setAccountNumber("197083240808");
		account.setBalance(827090.0);
		account.setActive(true);

		User user = new User();
		user.setAccount(account);
		user.setRole(Role.CUSTOMER);

		when(userService.getCurrentUser()).thenReturn(user);
		when(accountService.deposit(accountId, transaction, user)).thenReturn(account);

		mockMvc.perform(post("/api/account/{accountId}/deposit", accountId).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(transaction))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.status").value("SUCCESS"))
				.andExpect(jsonPath("$.message").value("Money deposited successfully."))
				.andExpect(jsonPath("$.data.id").value(account.getId()))
				.andExpect(jsonPath("$.data.accountNumber").value(account.getAccountNumber()))
				.andExpect(jsonPath("$.data.balance").value(account.getBalance()))
				.andExpect(jsonPath("$.data.active").value(account.isActive()));
	}

	@Test
	public void testDeposit_Failure_UserIsNotCustomer() throws Exception {
		TransactionDTO transaction = new TransactionDTO();

		User user = new User();
		user.setAccount(null);
		user.setRole(Role.BRANCH_MANAGER);

//		when(userService.getCurrentUser()).thenThrow(new CustomerNotFoundException(ApiMessages.NOT_A_CUSTOMER_ERROR));

		mockMvc.perform(post("/api/account/{accountId}/withdraw", "123").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(transaction))).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.status").value("ERROR"))
				.andExpect(jsonPath("$.message").value(ApiMessages.NOT_A_CUSTOMER_ERROR));
	}

	@Test
	public void testWithdraw_Success() throws Exception {
		String accountId = "12345";
		TransactionDTO transaction = new TransactionDTO();

		Account account = new Account();
		account.setId("db37bf87-63a8-400d-8fda-4638a5fb3635");
		account.setAccountNumber("197083240808");
		account.setBalance(827090.0);
		account.setActive(true);

		User user = new User();
		user.setAccount(account);

		when(userService.getCurrentUser()).thenReturn(user);
		when(accountService.withdraw(accountId, transaction, user)).thenReturn(account);

		mockMvc.perform(post("/api/account/{accountId}/withdraw", accountId).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(transaction))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.status").value("SUCCESS"))
				.andExpect(jsonPath("$.message").value("Money withdrawn successfully."))
				.andExpect(jsonPath("$.data.id").value(account.getId()))
				.andExpect(jsonPath("$.data.accountNumber").value(account.getAccountNumber()))
				.andExpect(jsonPath("$.data.balance").value(account.getBalance()))
				.andExpect(jsonPath("$.data.active").value(account.isActive()));
	}

	@Test
	public void testGetAccountById_Success() throws Exception {
		String accountId = "12345";
		Account account = new Account();
		account.setId("db37bf87-63a8-400d-8fda-4638a5fb3635");
		account.setAccountNumber("197083240808");
		account.setBalance(827090.0);
		account.setActive(true);

		when(accountService.getAccountById(accountId)).thenReturn(account);

		mockMvc.perform(get("/api/account/{accountId}", accountId).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.status").value("SUCCESS"))
				.andExpect(jsonPath("$.message").value("Account fetched successfully."))
				.andExpect(jsonPath("$.data.id").value(account.getId()))
				.andExpect(jsonPath("$.data.accountNumber").value(account.getAccountNumber()))
				.andExpect(jsonPath("$.data.balance").value(account.getBalance()))
				.andExpect(jsonPath("$.data.active").value(account.isActive()));
	}
}
