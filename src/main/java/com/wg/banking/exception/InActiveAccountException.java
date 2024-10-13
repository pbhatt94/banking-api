package com.wg.banking.exception;

public class InActiveAccountException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InActiveAccountException(String message) {
		super(message);
	}
}
