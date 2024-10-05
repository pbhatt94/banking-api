package com.wg.banking.helper.printer;

import java.util.List;

import com.wg.banking.model.Notification;

public class NotificationPrinter {

    private static final String HEADER_FORMAT = "%5s | %-15s | %-45s";
    private static final String ROW_FORMAT = "%5d | %-15s | %-45s";

    private static final String BOX_BORDER = "===============================================================================================================================";
    private static final String DIVIDER_LINE = "-----------------------------------------------------------------------------------------------------------------------------";


    public static void printNotifications(List<Notification> notifications) {
    	if(notifications == null || notifications.size() == 0) {
    		System.out.println("No notifications !");
    		return;
    	}
        // Print table title
        System.out.println(BOX_BORDER);
        System.out.println(centerTextInBox("NOTIFICATIONS"));
        System.out.println(BOX_BORDER);

        // Print header
        System.out.printf(HEADER_FORMAT, "S.No.", "Date", "Message");
        System.out.println();
        System.out.println(DIVIDER_LINE);

        // Print rows
        int index = 1;
        for (Notification notification : notifications) {
            try {
                // Print each notification row
                System.out.printf(ROW_FORMAT,
                        index++,
                        notification.getCreatedAt(),
                        notification.getMessage()
                		);
                System.out.println();
                System.out.println(DIVIDER_LINE);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error occurred while printing notification: " + notification);
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
