package com.wg.banking.helper.printer;

import java.util.List;

import com.wg.banking.helper.MaskString;
import com.wg.banking.model.Transaction;

public class TransactionPrinter {

    // Define the format for headers and rows
    private static final String HEADER_FORMAT = "%-15s | %-10s | %-12s | %-15s | %-15s | %-19s";
    private static final String ROW_FORMAT = "%-15s | %-10.2f | %-12s | %-15s | %-15s | %-19s";

    private static final String BOX_BORDER = "=======================================================================================================================";
    private static final String DIVIDER_LINE = "---------------------------------------------------------------------------------------------------------------------";
 
    public static void printTransactions(List<Transaction> transactions) {
    	if(transactions == null || transactions.size() == 0) {
    		System.out.println("There are no transactions !");
    		return;
    	}
        System.out.println(BOX_BORDER);
        System.out.println(centerTextInBox("TRANSACTION DETAILS"));
        System.out.println(BOX_BORDER);
 
        System.out.printf(HEADER_FORMAT, "Transaction ID", "Amount", "Type", "Source Account", "Target Account", "Created At");
        System.out.println();
        System.out.println(DIVIDER_LINE);
 
        for (Transaction transaction : transactions) {
            try {
                System.out.printf(ROW_FORMAT,
                    MaskString.maskString(transaction.getTransactionId(), 4),
                    transaction.getAmount(),
                    transaction.getTransactionType(),
                    transaction.getSourceAccountId() != null ? transaction.getSourceAccountId() : "N/A",
                    transaction.getDestinationAccountId() != null ? transaction.getDestinationAccountId() : "N/A",
                    transaction.getCreatedAt().toString()
                );
                System.out.println();
                System.out.println(DIVIDER_LINE);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error occurred while printing transaction: " + transaction);
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
