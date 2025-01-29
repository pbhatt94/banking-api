package com.wg.banking.exception;

public class IllegalResourceException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalResourceException(String message) {
        super(message);
    }
}
