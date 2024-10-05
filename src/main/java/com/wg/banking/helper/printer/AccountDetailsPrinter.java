package com.wg.banking.helper.printer;

import java.util.List;

import com.wg.banking.model.AccountDetails;

public class AccountDetailsPrinter {

    // Define the format for headers and rows
    private static final String HEADER_FORMAT = "%-15s | %-30s | %-15s | %-25s";
    private static final String ROW_FORMAT = "%-15s | %-30s | %-15.2f | %-25s";

    private static final String BOX_BORDER = "=======================================================================================";
    private static final String DIVIDER_LINE = "-------------------------------------------------------------------------------------";

    public static void printAccountDetails(List<AccountDetails> accounts) {
    	if(accounts == null || accounts.size() == 0) {
    		System.out.println("Nothing to print !");
    		return;
    	}
    	 
        // Print table title
        System.out.println(BOX_BORDER);
        System.out.println(centerTextInBox("ACCOUNT DETAILS"));
        System.out.println(BOX_BORDER);

        // Print header
        System.out.printf(HEADER_FORMAT, "Account No", "Owner Name", "Balance", "Branch");
        System.out.println();
        System.out.println(DIVIDER_LINE);

        // Print rows
        for (AccountDetails account : accounts) {
            try {
                // Print each account row
                System.out.printf(ROW_FORMAT,
                        account.getAccountNo(),
                        account.getOwnerName(),
                        account.getBalance(),
                        account.getBranch()
                );
                System.out.println();
                System.out.println(DIVIDER_LINE);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error occurred while printing account: " + account);
            }
        }
    }

    // Helper method to center the text within a box
    private static String centerTextInBox(String text) {
        int boxWidth = BOX_BORDER.length();
        int textLength = text.length();
        int padding = (boxWidth - textLength) / 2;

        // Creating a line with centered text surrounded by spaces
        StringBuilder centeredText = new StringBuilder();
        centeredText.append(" ".repeat(padding));
        centeredText.append(text);
        centeredText.append(" ".repeat(padding));

        // Ensure the line is exactly as wide as the box, accounting for odd width
        while (centeredText.length() < boxWidth) {
            centeredText.append(" ");
        }

        return centeredText.toString();
    }
}
