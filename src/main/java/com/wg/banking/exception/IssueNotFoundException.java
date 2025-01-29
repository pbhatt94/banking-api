package com.wg.banking.exception;

public class IssueNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IssueNotFoundException(String message) {
		super(message);
	}
}
