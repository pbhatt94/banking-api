package com.wg.banking.constants;

public class ClosedAccountsConstants {

    // Table name
    public static final String TABLE_NAME = "ClosedAccounts";

    // Column names
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_CLOSED_AT = "closed_at";

    // Private constructor to prevent instantiation
    private ClosedAccountsConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
