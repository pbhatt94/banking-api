package com.wg.banking.helper;

public class UnauthenticatedException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnauthenticatedException() {
        super("User is not authenticated.");
    }

    public UnauthenticatedException(String message) {
        super(message);
    }
}
