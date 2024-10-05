package com.wg.banking.helper;

import java.util.regex.Pattern;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
	
	private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[A-Z])" +        // At least one uppercase letter
            "(?=.*[a-z])" +        // At least one lowercase letter
            "(?=.*[0-9])" +        // At least one digit
            "(?=.*[!@#$%^&*(),.?\":{}|<>])" + // At least one special character
            ".{8,}$"               // At least 8 characters long
        );


    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    public static boolean isPasswordStrong(String password) {
    	if(password == null || password.length() < 8) return false;
        return PASSWORD_PATTERN.matcher(password).matches();
    }
}