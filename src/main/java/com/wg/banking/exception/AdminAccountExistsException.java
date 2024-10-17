package com.wg.banking.exception;

public class AdminAccountExistsException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdminAccountExistsException(String message) {
		super(message);
	}
}
