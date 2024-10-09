package com.wg.banking.exception;

public class MissingTransactionDetailsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MissingTransactionDetailsException(String message) {
		super(message);
	}
}
