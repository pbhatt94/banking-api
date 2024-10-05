package com.wg.banking.helper.printer;

import java.util.List;

import com.wg.banking.model.Issue;

public class IssuePrinter {
 
	private static final String HEADER_FORMAT = "%5s | %-30s | %-15s | %-5s";
	private static final String ROW_FORMAT = "%5d | %-30s | %-15s | %15s";

    private static final String BOX_BORDER = "==================================================================================================================================================";
    private static final String DIVIDER_LINE = "------------------------------------------------------------------------------------------------------------------------------------------------";
 
    public static void printIssues(List<Issue> issues) {
    	if(issues == null || issues.size() == 0) {
    		System.out.println("There are no issues !");
    		return;
    	}
        // Print table title
        System.out.println(BOX_BORDER);
        System.out.println(centerTextInBox("Issue DETAILS"));
        System.out.println(BOX_BORDER);
 
        // Print header
        System.out.printf(HEADER_FORMAT,"S.No.", "Issue","Issue Status", "Date");
        System.out.println();
        System.out.println(DIVIDER_LINE);
 
        // Print rows
        int index = 1;
        for (Issue issue : issues) {
            try {
                // Print each account row
                System.out.printf(ROW_FORMAT,
                		index++,
                		issue.getMessage(),
                		issue.getStatus(),
                		issue.getCreatedAt()
                );
                System.out.println();
                System.out.println(DIVIDER_LINE);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error occurred while printing user: " + issue);
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
