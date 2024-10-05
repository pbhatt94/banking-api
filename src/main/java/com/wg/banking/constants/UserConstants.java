package com.wg.banking.constants;

public final class UserConstants {
	private UserConstants() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}
	
	public static final String USER_TABLE_NAME = "User";
    
    public static final String USER_ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";
    public static final String EMAIL_COLUMN = "email";
    public static final String USERNAME_COLUMN = "username";
    public static final String PASSWORD_COLUMN = "password";
    public static final String AGE_COLUMN = "age";
    public static final String GENDER_COLUMN = "gender";
    public static final String PHONE_NO_COLUMN = "phoneNo";
    public static final String ADDRESS_COLUMN = "address";
    public static final String ROLE_COLUMN = "role";
    public static final String CREATED_AT_COLUMN = "createdAt";
    public static final String UPDATED_AT_COLUMN = "updatedAt";
    
    
    public static final String ADMIN = "ADMIN";
    public static final String BRANCH_MANAGER = "BRANCH_MANAGER";
    public static final String CUSTOMER = "CUSTOMER";
}
