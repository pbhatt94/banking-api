package com.wg.banking.constants;

public final class ApiMessages {
	public static final String AMOUNT_NEGATIVE_ERROR = "Amount must be greater than 0.";
	public static final String INSUFFICIENT_BALANCE_ERROR = "Not Sufficient Balance in account.";
	public static final String NOT_A_CUSTOMER_ERROR = "Must be a customer for performing withdrawal.";
	public static final String INVALID_ACCOUNT_NUMBER = "Invalid Account Number.";
	public static final String SOURCE_CANNOT_BE_SAME_AS_TARGET_ERROR = "Source account can't be same as target.";
	public static final String INVALID_CREDENTIALS_MESSAGE = "Incorrect username or password.";
	public static final String USER_NOT_FOUND_ERROR = "User not found.";
	public static final String ENTER_A_VALID_ACCOUNT_NUMBER = "Enter a valid Account Number";
	public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
	public static final String UNEXPECTED_ERROR_MESSAGE = "An unexpected error occurred";
	public static final String CUSTOMER_NOT_FOUND_MESSAGE = "Customer not found in the database";
	public static final String ACCOUNT_NOT_FOUND_MESSAGE = "Account not found in the database";
	public static final String ENTER_VALID_AMOUNT_MESSAGE = "Enter a valid Amount (greater than 0)";
	public static final String USERS_FETCHED_SUCCESSFULLY = "Users fetched successfully.";
	public static final String USER_CREATED_SUCCESSFULLY = "User created successfully.";
	public static final String LOGGED_IN_SUCCESSFULLY = "Logged in successfully.";
	public static final String USER_DELETED_SUCCESSFULLY = "User deleted successfully.";
	public static final String FAILED_TO_DELETE_USER = "User could not be deleted.";
	public static final String TRANSFER_SUCCESSFUL_MESSAGE = "Money transferred successfully.";
	public static final String WITHDRAWAL_SUCCESSFUL_MESSAGE = "Money withdrawn successfully.";
	public static final String DEPOSIT_SUCCESSFUL_MESSAGE = "Money deposited successfully.";
	public static final String USER_FOUND_SUCCESSFULLY = "User found successfully.";
	public static final String USER_UPDATED_SUCCESSFULLY = "User updated successfully.";
	public static final String BAD_REQUEST_MESSAGE = "Bad Request.";
	public static final String MISSING_TRANSACTION_DETAILS_MESSAGE = "Missing amount or account number.";
	public static final String TRANSACTIONS_FETCHED_SUCCESSFULLY_MESSAGE = "Transactions fetched successfully.";
	public static final String VALIDATION_FAILED_ERROR = "Validation failed.";
	public static final String INVALID_REQUEST = "Invalid Request.";
	public static final String ACESS_DENIED_ERROR = "You do not have permission to access this resouce.";
	public static final String INVALID_PAGE_NUMBER_OR_LIMIT = "Invalid page number or limit.";
	public static final String PAGE_NUMBER_AND_LIMIT_MUST_BE_POSITIVE = "Page number and limit must be greater than 0.";
}
