package com.wg.banking.exception;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wg.banking.constants.ApiMessages;
import com.wg.banking.dto.ApiError;
import com.wg.banking.dto.ApiResponseHandler;
import com.wg.banking.model.ApiResponseStatus;
import com.wg.banking.service.impl.InvalidInputException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<Object> handleCustomerNotFound(CustomerNotFoundException ex, HttpServletRequest request)
			throws IOException {
		logError(ex, request);
		ApiError apiError = new ApiError(LocalDateTime.now(), ex.getMessage(), ApiMessages.CUSTOMER_NOT_FOUND_MESSAGE);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.NOT_FOUND,
				ApiMessages.CUSTOMER_NOT_FOUND_MESSAGE, null, apiError);
	}
	
	@ExceptionHandler(IssueNotFoundException.class)
	public ResponseEntity<Object> handleIssueNotFound(IssueNotFoundException ex, HttpServletRequest request)
			throws IOException {
		logError(ex, request);
		ApiError apiError = new ApiError(LocalDateTime.now(), ex.getMessage(), ApiMessages.ISSUE_NOT_FOUND);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.NOT_FOUND,
				ApiMessages.ISSUE_NOT_FOUND, null, apiError);
	}

	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<Object> handleAccountNotFound(AccountNotFoundException ex, HttpServletRequest request)
			throws IOException {
		logError(ex, request);
		ApiError apiError = new ApiError(LocalDateTime.now(), ex.getMessage(), ApiMessages.ACCOUNT_NOT_FOUND_MESSAGE);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.NOT_FOUND,
				ApiMessages.ACCOUNT_NOT_FOUND_MESSAGE, null, apiError);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex, HttpServletRequest request)
			throws IOException {
		logError(ex, request);
		ApiError apiError = new ApiError(LocalDateTime.now(), ex.getMessage(), ApiMessages.USER_NOT_FOUND_ERROR);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.NOT_FOUND,
				ApiMessages.USER_NOT_FOUND_ERROR, null, apiError);
	}

	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<Object> handleInsufficientBalance(InsufficientBalanceException ex, HttpServletRequest request)
			throws IOException {
		logError(ex, request);
		ApiError apiError = new ApiError(LocalDateTime.now(), ex.getMessage(), ApiMessages.INSUFFICIENT_BALANCE_ERROR);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.BAD_REQUEST,
				ApiMessages.INSUFFICIENT_BALANCE_ERROR, null, apiError);
	}

	@ExceptionHandler(MissingTransactionDetailsException.class)
	public ResponseEntity<Object> handleMissingTransactionDetails(MissingTransactionDetailsException ex,
			HttpServletRequest request) throws IOException {
		logError(ex, request);
		ApiError apiError = new ApiError(LocalDateTime.now(), ex.getMessage(),
				ApiMessages.MISSING_TRANSACTION_DETAILS_MESSAGE);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.BAD_REQUEST,
				ApiMessages.MISSING_TRANSACTION_DETAILS_MESSAGE, null, apiError);
	}

	@ExceptionHandler(InvalidAmountException.class)
	public ResponseEntity<Object> handleInvalidAmount(InvalidAmountException ex, HttpServletRequest request)
			throws IOException {
		logError(ex, request);
		ApiError apiError = new ApiError(LocalDateTime.now(), ex.getMessage(), ApiMessages.ENTER_VALID_AMOUNT_MESSAGE);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.BAD_REQUEST,
				ApiMessages.ENTER_VALID_AMOUNT_MESSAGE, null, apiError);
	}

	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<Object> handleInvalidInput(InvalidInputException ex, HttpServletRequest request)
			throws IOException {
		logError(ex, request);
		ApiError apiError = new ApiError(LocalDateTime.now(), ex.getMessage(),
				ApiMessages.PAGE_NUMBER_AND_LIMIT_MUST_BE_POSITIVE);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.BAD_REQUEST,
				ApiMessages.INVALID_PAGE_NUMBER_OR_LIMIT, null, apiError);
	}

	@ExceptionHandler(SourceSameAsTargetException.class)
	public ResponseEntity<Object> handleSourceSameAsTarget(SourceSameAsTargetException ex, HttpServletRequest request)
			throws IOException {
		logError(ex, request);
		ApiError apiError = new ApiError(LocalDateTime.now(), ex.getMessage(),
				ApiMessages.ENTER_A_VALID_ACCOUNT_NUMBER);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.BAD_REQUEST,
				ApiMessages.ENTER_A_VALID_ACCOUNT_NUMBER, null, apiError);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Object> handleBadCredentials(BadCredentialsException ex, HttpServletRequest request)
			throws IOException {
		logError(ex, request);
		ApiError apiError = new ApiError(LocalDateTime.now(), ex.getMessage(), ApiMessages.INVALID_CREDENTIALS_MESSAGE);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.UNAUTHORIZED,
				ApiMessages.INVALID_CREDENTIALS_MESSAGE, null, apiError);
	}

	@ExceptionHandler(AdminAccountExistsException.class)
	public ResponseEntity<Object> handleAdminAlreadyExists(AdminAccountExistsException ex, HttpServletRequest request)
			throws IOException {
		logError(ex, request);
		ApiError apiError = new ApiError(LocalDateTime.now(), ex.getMessage(),
				ApiMessages.ADMIN_ALREADY_EXISTS_MESSAGE);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.BAD_REQUEST,
				ApiMessages.ADMIN_ALREADY_EXISTS_MESSAGE, null, apiError);
	}

	@ExceptionHandler(IllegalResourceException.class)
	public ResponseEntity<Object> handleAdminAlreadyExists(IllegalResourceException ex, HttpServletRequest request)
			throws IOException {
		logError(ex, request);
		ApiError apiError = new ApiError(LocalDateTime.now(), ex.getMessage(),
				ApiMessages.ADMIN_ALREADY_EXISTS_MESSAGE);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.BAD_REQUEST,
				ApiMessages.ADMIN_ALREADY_EXISTS_MESSAGE, null, apiError);
	}

	@ExceptionHandler(ResourceAccessDeniedException.class)
	public ResponseEntity<Object> handleAccessDeniedException(ResourceAccessDeniedException ex,
			HttpServletRequest request) throws IOException {
		logError(ex, request);
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
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex,
			HttpServletRequest request) throws IOException {
		logError(ex, request);
		ApiError apiError = new ApiError(LocalDateTime.now(), ApiMessages.INVALID_REQUEST, ex.getMessage(),
				List.of(ex.getMessage()));

		return ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.BAD_REQUEST,
				ApiMessages.INVALID_REQUEST, null, apiError);
	}

	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException ex, HttpServletRequest request)
			throws IOException {
		logError(ex, request);
		ApiError apiError = new ApiError(LocalDateTime.now(), ApiMessages.INVALID_REQUEST, ex.getMessage(),
				List.of(ex.getMessage()));

		return ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.UNAUTHORIZED,
				ApiMessages.JWT_EXPIRED_MESSAGE, null, apiError);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
			HttpServletRequest request) throws IOException {
		logError(ex, request);
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
	
	@ExceptionHandler(AuthorizationDeniedException.class)
	protected ResponseEntity<ApiError> handleAuthorizationDeniedException(AuthorizationDeniedException ex,
			WebRequest request) {
		ApiError errorDetails = new ApiError(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ApiError>(errorDetails, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGenericException(Exception ex, HttpServletRequest request) throws IOException {
		logError(ex, request);

		ApiError apiError = new ApiError(LocalDateTime.now(), ApiMessages.INTERNAL_SERVER_ERROR,
				ApiMessages.UNEXPECTED_ERROR_MESSAGE);
		return ApiResponseHandler.buildResponse(ApiResponseStatus.ERROR, HttpStatus.INTERNAL_SERVER_ERROR,
				ApiMessages.INTERNAL_SERVER_ERROR, null, apiError);
	}

	private void logError(Exception ex, HttpServletRequest request) throws IOException {
		logger.error("Error occurred: {}, URL: {}, Method: {}, User: {}, Params: {}, Body: {}", ex.getMessage(),
				request.getRequestURL(), request.getMethod(),
				request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : "Anonymous",
				request.getParameterMap(),
				request.getContentLength() > 0
						? request.getReader().lines().collect(Collectors.joining(System.lineSeparator()))
						: "N/A",
				ex);
	}
}