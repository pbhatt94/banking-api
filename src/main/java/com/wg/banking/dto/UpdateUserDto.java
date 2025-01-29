package com.wg.banking.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UpdateUserDto {
	private String userId;

	@Size(min = 1, message = "Name must not be empty.")
	private String name;

	@NotBlank(message = "Email must not be null.")
	@Email
	private String email;

	@NotBlank(message = "Username must not be null.")
	@Size(min = 5, message = "Username must be unique and atleast 5 characters long.")
	private String username;

	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must be at least 8 characters long and contain upper and lower case letters, numbers, and special characters.")
	private String password;

	@Min(value = 18, message = "Age must be at least 18.")
	@Max(value = 120, message = "Age must be less than 120.")
	private int age;

	@NotBlank(message = "Phone number must not be null.")
	@Size(min = 10, max = 10, message = "Phone number must be exactly 10 digits and numeric.")
	@Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits and numeric.")
	private String phoneNo;

	@NotBlank(message = "Address must not be null.")
	private String address;
}