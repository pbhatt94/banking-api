package com.wg.banking.helper;

public class InsufficientBalanceException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientBalanceException() {
        super("Insufficient Balance. Transaction failed!");
    }

    public InsufficientBalanceException(String message) {
        super(message);
    }
}
