package com.wg.banking.helper;

import java.util.Scanner;

public class GetUserInput {

	public static int getUserChoice() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String input = scanner.nextLine();
			try {
				int value = Integer.parseInt(input);
				return value;
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid integer.");
			}
		}
	}

	public static double getDoubleInput() {
		Scanner scanner = new Scanner(System.in);
		double value = 0;
		boolean valid = false;

		while (!valid) {
			System.out.print("Enter your choice: ");

			if (scanner.hasNextDouble()) {
				value = scanner.nextDouble();
				valid = true;
			} else {
				System.out.println("Invalid input. Please enter a valid Input.");
				scanner.next();
			}
		}

		return value;
	}
}