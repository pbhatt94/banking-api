package com.wg.banking.constants;

public class IssueConstants {
	public static final String ISSUE_TABLE_NAME = "Issue";
    public static final String ISSUE_ID_COLUMN = "issue_id";
    public static final String MESSAGE_COLUMN = "message";
    public static final String USER_ID_COLUMN = "user_id";
    public static final String STATUS_COLUMN = "status";
    public static final String CREATED_AT_COLUMN = "createdAt";
        
    private IssueConstants() {
    	throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
