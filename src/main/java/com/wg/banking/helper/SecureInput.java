package com.wg.banking.helper;

import java.io.Console;

public class SecureInput {
	public static String getSecureInput(String input) {
		Console console = System.console();

		if (console == null) {
			System.out.println("No console available");
			return null;
		}

		char[] inputFromConsole = console.readPassword("Enter your " + input + ":\n");
		String result = new String(inputFromConsole);
		return result;
	}
}