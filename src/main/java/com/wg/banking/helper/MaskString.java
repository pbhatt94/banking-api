package com.wg.banking.helper;

public class MaskString {
	public static String maskString(String input, int visibleCount) {
        if (input == null || input.length() <= visibleCount) {
            return input;
        }

        int length = input.length();

        StringBuilder masked = new StringBuilder();

        for (int i = 0; i < length - visibleCount; i++) {
            masked.append('*');
        }

        masked.append(input.substring(length - visibleCount));

        return masked.toString();
    }

}
