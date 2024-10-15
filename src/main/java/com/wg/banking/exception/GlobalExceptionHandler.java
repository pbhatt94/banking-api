package com.wg.banking.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.wg.banking.constants.ApiMessages;
import com.wg.banking.dto.ApiError;
import com.wg.banking.dto.ApiResponseHandler;
import com.wg.banking.model.ApiResponseStatus;
import com.wg.banking.service.impl.InvalidInputException;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<Object> handleCustomerNotFound(CustomerNotFoundException ex) {
		ApiError apiError = new ApiError(LocalDateTime.now(), ex.getMessage(), ApiMessages.CUSTOMER_NOT_FOUND_MESSAGE);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.NOT_FOUND,
				ApiMessages.CUSTOMER_NOT_FOUND_MESSAGE, null, apiError);
	}

	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<Object> handleAccountNotFound(AccountNotFoundException ex) {
		ApiError apiError = new ApiError(LocalDateTime.now(), ex.getMessage(), ApiMessages.ACCOUNT_NOT_FOUND_MESSAGE);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.NOT_FOUND,
				ApiMessages.ACCOUNT_NOT_FOUND_MESSAGE, null, apiError);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex) {
		ApiError apiError = new ApiError(LocalDateTime.now(), ex.getMessage(), ApiMessages.USER_NOT_FOUND_ERROR);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.NOT_FOUND,
				ApiMessages.USER_NOT_FOUND_ERROR, null, apiError);
	}

	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<Object> handleInsufficientBalance(InsufficientBalanceException ex) {
		ApiError apiError = new ApiError(LocalDateTime.now(), ex.getMessage(), ApiMessages.INSUFFICIENT_BALANCE_ERROR);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.BAD_REQUEST,
				ApiMessages.INSUFFICIENT_BALANCE_ERROR, null, apiError);
	}

	@ExceptionHandler(MissingTransactionDetailsException.class)
	public ResponseEntity<Object> handleMissingTransactionDetails(MissingTransactionDetailsException ex) {
		ApiError apiError = new ApiError(LocalDateTime.now(), ex.getMessage(),
				ApiMessages.MISSING_TRANSACTION_DETAILS_MESSAGE);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.BAD_REQUEST,
				ApiMessages.MISSING_TRANSACTION_DETAILS_MESSAGE, null, apiError);
	}

	@ExceptionHandler(InvalidAmountException.class)
	public ResponseEntity<Object> handleInvalidAmount(InvalidAmountException ex) {
		ApiError apiError = new ApiError(LocalDateTime.now(), ex.getMessage(), ApiMessages.ENTER_VALID_AMOUNT_MESSAGE);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.BAD_REQUEST,
				ApiMessages.ENTER_VALID_AMOUNT_MESSAGE, null, apiError);
	}

	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<Object> handleInvalidInput(InvalidInputException ex) {
		ApiError apiError = new ApiError(LocalDateTime.now(), ex.getMessage(),
				ApiMessages.PAGE_NUMBER_AND_LIMIT_MUST_BE_POSITIVE);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.BAD_REQUEST,
				ApiMessages.INVALID_PAGE_NUMBER_OR_LIMIT, null, apiError);
	}

	@ExceptionHandler(SourceSameAsTargetException.class)
	public ResponseEntity<Object> handleSourceSameAsTarget(SourceSameAsTargetException ex) {
		ApiError apiError = new ApiError(LocalDateTime.now(), ex.getMessage(),
				ApiMessages.ENTER_A_VALID_ACCOUNT_NUMBER);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.BAD_REQUEST,
				ApiMessages.ENTER_A_VALID_ACCOUNT_NUMBER, null, apiError);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Object> handleBadCredentials(BadCredentialsException ex) {
		ApiError apiError = new ApiError(LocalDateTime.now(), ex.getMessage(), ApiMessages.INVALID_CREDENTIALS_MESSAGE);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.UNAUTHORIZED,
				ApiMessages.INVALID_CREDENTIALS_MESSAGE, null, apiError);
	}

	@ExceptionHandler(ResourceAccessDeniedException.class)
	public ResponseEntity<Object> handleAccessDeniedException(ResourceAccessDeniedException ex) {
		ApiError apiError = new ApiError(LocalDateTime.now(), ex.getMessage(), ApiMessages.ACESS_DENIED_ERROR);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.UNAUTHORIZED,
				ApiMessages.ACESS_DENIED_ERROR, null, apiError);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		List<String> errors = new ArrayList<>();

		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			errors.add(fieldError.getDefaultMessage());
		}

		ApiError apiError = new ApiError(LocalDateTime.now(), "Total validation errors: " + ex.getErrorCount(),
				request.getDescription(false), errors);
		return new ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
		ApiError apiError = new ApiError(LocalDateTime.now(), ApiMessages.INVALID_REQUEST, ex.getMessage(),
				List.of(ex.getMessage()));

		return ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.BAD_REQUEST,
				ApiMessages.INVALID_REQUEST, null, apiError);
	}

	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException ex) {
		ApiError apiError = new ApiError(LocalDateTime.now(), ApiMessages.INVALID_REQUEST, ex.getMessage(),
				List.of(ex.getMessage()));

		return ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.UNAUTHORIZED,
				ApiMessages.JWT_EXPIRED_MESSAGE, null, apiError);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
		String conflictMessage = extractConflictMessage(ex.getMessage());
		ApiError apiError = new ApiError(LocalDateTime.now(), conflictMessage, null, null);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.CONFLICT, conflictMessage, null,
				apiError);
	}

	private String extractConflictMessage(String message) {
		Pattern pattern = Pattern.compile("Duplicate entry '(.*?)' for key '(.*?)'");
		Matcher matcher = pattern.matcher(message);
		if (matcher.find()) {
			return "A conflict occurred with entry: " + matcher.group(1);
		}
		return ApiMessages.CONFLICT_MESSAGE;
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGenericException(Exception ex) {
		ApiError apiError = new ApiError(LocalDateTime.now(), ApiMessages.INTERNAL_SERVER_ERROR,
				ApiMessages.UNEXPECTED_ERROR_MESSAGE);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.INTERNAL_SERVER_ERROR,
				ApiMessages.INTERNAL_SERVER_ERROR, null, apiError);
	}
}