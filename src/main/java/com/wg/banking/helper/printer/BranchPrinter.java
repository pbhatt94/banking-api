package com.wg.banking.helper.printer;

import java.util.List;

import com.wg.banking.model.Branch;

public class BranchPrinter {
 
	private static final String HEADER_FORMAT = "%5s | %-30s | %-35s";
	private static final String ROW_FORMAT = "%5d | %-30s | %-35s";

    private static final String BOX_BORDER = "==================================================================================================================================================";
    private static final String DIVIDER_LINE = "------------------------------------------------------------------------------------------------------------------------------------------------";
 
    public static void printBranches(List<Branch> branches) {
    	if(branches == null || branches.size() == 0) {
    		System.out.println("There are no branches !");
    		return;
    	}
        // Print table  
        System.out.println(BOX_BORDER);
        System.out.println(centerTextInBox("BRANCH DETAILS"));
        System.out.println(BOX_BORDER);
 
        // Print header
        System.out.printf(HEADER_FORMAT,"S.No.", "Branch Name","Branch Address");
        System.out.println();
        System.out.println(DIVIDER_LINE);
 
        // Print rows
        int index = 1;
        for (Branch branch : branches) {
            try {
                // Print each account row
                System.out.printf(ROW_FORMAT,
                		index++,
                		branch.getBranchName(),
                		branch.getBranchAddress()
                );
                System.out.println();
                System.out.println(DIVIDER_LINE);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error occurred while printing branch: " + branch);
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
