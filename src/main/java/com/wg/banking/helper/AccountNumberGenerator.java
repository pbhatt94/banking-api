package com.wg.banking.helper;

import java.security.SecureRandom;

public class AccountNumberGenerator {

    private static final String DIGITS = "0123456789";
    private static final SecureRandom random = new SecureRandom();
    
    private static final int LENGTH = 12;
    
    public static String generateAccountNumber() {
        StringBuilder numericString = new StringBuilder(LENGTH);

        for (int i = 0; i < LENGTH; i++) {
            int randomIndex = random.nextInt(DIGITS.length());
            char randomDigit = DIGITS.charAt(randomIndex);
            numericString.append(randomDigit);
        }

        return numericString.toString();
    }
}
