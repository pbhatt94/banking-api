package com.wg.banking.exception;

public class ResourceAccessDeniedException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceAccessDeniedException(String message) {
		super(message);
	}
}