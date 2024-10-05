package com.wg.banking.constants;

public final class AccountConstants { 
    
    public static final String ACCOUNT_TABLE_NAME = "Account";
    public static final String ACCOUNT_NO_COLUMN = "account_no";
    public static final String BALANCE_COLUMN = "balance";
    public static final String OWNER_ID_COLUMN = "owner_id";
    public static final String BRANCH_ID_COLUMN = "branch_id";
    public static final String CREATED_AT_COLUMN = "createdAt";
    public static final String UPDATED_AT_COLUMN = "updatedAt";
    
    
    public static final double DEFAULT_ACCOUNT_BALANCE = 0.0;
    
    private AccountConstants() {
    	throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}