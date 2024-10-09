package com.wg.banking.exception;

public class SourceSameAsTargetException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SourceSameAsTargetException(String message) {
		super(message);
	}
}
