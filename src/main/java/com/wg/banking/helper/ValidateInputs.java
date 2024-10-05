package com.wg.banking.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wg.banking.model.User;

public class ValidateInputs {
	
	public static boolean isValidEmail(String email) {
		if (email == null) 
            return false; 
        
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        return pat.matcher(email).matches(); 
    }
	
	public static boolean isValidString(String str) {
		if(str == null) return false;
		return !str.trim().isEmpty();
	}
	
	public static boolean isValidName(String name) {
		String namePattern = "^[A-Za-z]+(?:\\s[A-Za-z]+)*$";
        return name != null && name.matches(namePattern);
	}
	
	public static boolean isValidAge(int age) {
		return age >=18 && age<150;
	}
	
	public static boolean isValidGender(String gender) {
		if(gender == null) return false;
		return gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F") || gender.equalsIgnoreCase("O");
	}
	
	public static boolean isValidUsername(String username) {
		if (username == null) {
			return false;
		}
		Pattern USERNAME_PATTERN = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{4,30}$");
		Matcher matcher = USERNAME_PATTERN.matcher(username);
		return matcher.matches();
	}
	
	public static boolean isValidPhoneNo(String phoneNo) {
		if (phoneNo == null) {
            return false;
        }
		Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^\\d{10}$");
        Matcher matcher = PHONE_NUMBER_PATTERN.matcher(phoneNo);
        return matcher.matches();
	}
	
	public static boolean isValidRole(String role) {
		if(role == null) return false;
		return role.equalsIgnoreCase("BRANCH_MANAGER") || role.equalsIgnoreCase("CUSTOMER");
	}
	
	
	public static boolean isValidNotificationType(String notificationType) {
		if(notificationType == null || notificationType.length() == 0) {
			return false;
		}
		return notificationType.equalsIgnoreCase("SYSTEM_ALERT") || notificationType.equalsIgnoreCase("ACCOUNT_ACTIVITY");
	}
	
	
	public static boolean validateUser(User user) {
		if(user == null) return false;
		return isValidEmail(user.getEmail()) && PasswordUtil.isPasswordStrong(user.getPassword()) && isValidUsername(user.getUsername()) && isValidGender(user.getGender().toString()) && isValidPhoneNo(user.getPhoneNo()) && isValidAge(user.getAge());
	}

}
