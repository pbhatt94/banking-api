package com.wg.banking.constants;

public final class BranchConstants {
	private BranchConstants() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}
	public static final String BRANCH_TABLE_NAME = "Branch";
    
    public static final String BRANCH_ID_COLUMN = "id";
    public static final String BRANCH_NAME_COLUMN = "name";
    public static final String BRANCH_ADDRESS_COLUMN = "address";
    public static final String BRANCH_MANAGER_COLUMN = "manager_id";
    public static final String CREATED_AT_COLUMN = "createdAt";
    public static final String UPDATED_AT_COLUMN = "updatedAt";
}
