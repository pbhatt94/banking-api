package com.wg.banking.constants;

public final class StringConstants {
	public static final String BorderLine = "+---------------+----------+------------+---------------+--------+----------+---------+---------+------+---------+---------+---------------+---------------+---------------+----------+------------+---------------+--------+----------+---------+---------+------+---------+---------+---------+---------+------+---------+---------+";
	public static final String Welcome = "======  Welcome to Bank Management System  ======";

	public static final String ADMIN_MENU = """
			\s
			------- ADMIN MENU -------
			1. Manage Users
			2. Manage Accounts
			3. Manage Branches
			4. Manage Notifications
			5. Manage Issues
			6. Manage Transactions
			7. Logout
			8. Exit
			\s
			Enter your choice:
			""";

	public static final String CUSTOMER_MENU = """
			\s
			----- CUSTOMER MENU -----
			1. Check Account Balance
			2. Withdraw Money
			3. Deposit Money
			4. Transfer Money
			5. View Previous Transactions
			6. View Profile Details
			7. Update Profile
			8. Check Notifications
			9. Raise Issue
			10. Check Issue Status
			11. Logout
			12. Exit
			\s
			Enter your choice:
			""";

	public static final String BRANCH_MANAGER_MENU = """
			\s
			----- BRANCH MANAGER MENU -----
			1. Manage Accounts
			2. Manage Issues
			3. Manage Notifications
			4. Manage Transactions
			5. Logout
			6. Exit
			\s
			Enter your choice:
			""";

	
	public static final String MANAGE_USER_SUB_MENU = """
			\s
			----- Manage Users -----
			1. Get All Users
			2. Add User
			3. Update User
			4. View Inactive Users
			5. Go Back
			6. Logout
			7. Exit
			\s
			Enter your choice:
			""";
	
	public static final String MANAGE_ACCOUNT_SUB_MENU = """
			\s
			----- Manage Accounts -----
			1. Get All Accounts
			2. Update Account
			3. Close Account
			4. Go Back
			5. Logout
			6. Exit
			\s
			Enter your choice:
			""";
	
	public static final String MANAGE_BRANCH_SUB_MENU = """
			\s
			----- Manage Branches -----
			1. Get All Branches
			2. Add Branch
			3. Update Branch
			4. Delete Branch
			5. Go Back
			6. Logout
			7. Exit
			\s
			Enter your choice:
			""";
	
	public static final String MANAGE_NOTIFICATION_SUB_MENU = """
			\s
			----- Manage Notifications -----
			1. Get All Notifications
			2. Send Notification
			3. Go Back
			4. Logout
			5. Exit
			\s
			Enter your choice:
			""";
	
	public static final String MANAGE_ISSUE_SUB_MENU = """
			\s
			----- Manage Issues -----
			1. View All Issues
			2. Resolve Issue
			3. Go Back
			4. Logout
			5. Exit
			\s
			Enter your choice:
			""";
	
	public static final String MANAGE_TRANSACTION_SUB_MENU = """
			\s
			----- Manage Transactions -----
			1. View All Transactions
			2. Filter by Transaction Type
			3. Filter by Amount			
			4. Go Back
			5. Logout
			6. Exit
			\s
			Enter your choice:
			""";
	
	public static final String MANAGE_TRANSACTION_FILTER_BY_TYPE_SUB_MENU = """
			\s
			----- Filter By Transaction Type-----
			1. View All Withdrawals
			2. View All Deposits
			3. View All Transfers			
			4. Go Back
			5. Logout
			6. Exit
			\s
			Enter your choice:
			""";
	
	public static final String MANAGE_TRANSACTION_FILTER_BY_AMOUNT_SUB_MENU = """
			\s
			----- Filter By Amount -----
			1. Enter Minimum Amount
			2. Enter Maximum Amount
			3. Specify a range			
			4. Go Back
			5. Logout
			6. Exit
			\s
			Enter your choice:
			""";
	
	public static final String GET_ACCOUNT_DETAILS_SUB_MENU = """
			\s
			----- Select the criteria to filter by -----
			1. Filter by Branch
			2. Filter by Account Balance
			3. Get All Accounts
			4. Go Back
			5. Logout
			6. Exit
			\s
			Enter your choice:
			""";
	
	public static final String GET_ACCOUNT_DETAILS_SUB_MENU_MANAGER = """
			\s
			----- Select the criteria to filter by -----
			1. Get All Accounts
			2. Filter by Account Balance
			3. Go Back
			4. Logout
			5. Exit
			\s
			Enter your choice:
			""";
	
	public static final String GET_ACCOUNT_DETAILS_FILTER_BY_BALANCE_SUB_MENU = """
			\s
			----- Select the criteria to filter by -----
			1. Filter by Minumum Balance
			2. Filter by Maximum Balance
			3. Specify a range
			4. Go Back
			5. Logout
			6. Exit
			\s
			Enter your choice:
			""";
	
	public static final String GET_ALL_NOTIFICATIONS_SUB_MENU = """
			\s
			----- Select the criteria to filter by -----
			1. System Alerts
			2. Account Activity Notifications
			3. Go Back
			\s
			Enter your choice:
			""";
	
	public static final String UPDATE_USER_MENU = """
            Which field would you like to update?
            1. Email
            2. Username
            3. Password
            4. Age
            5. Gender
            6. Phone Number
            7. Address
            8. Exit
            """;
	
	
	public static final String ACCOUNT_UPDATE_MENU = """
		    \nWhich field would you like to update?
		    1. Account Owner
		    2. Branch
		    3. Exit
		    """;
	
	public static final String BRANCH_UPDATE_MENU = """
		    \nWhich field would you like to update?
		    1. Branch Name
		    2. Branch Address
		    3. Branch Manager
		    4. Exit
		    """;
	
	public static final String WELCOME_MESSAGE = "--------------------Welcome to the Banking System!-----------------";
	public static final String ENTER_BRANCH_NAME_MESSAGE = "Enter branch name: ";
	public static final String INVALID_INPUT_MESSAGE = "Invalid Input! Please try again!";
	public static final String ENTER_BRANCH_ADDRESS_MESSAGE = "Enter branch address: ";
	public static final String ENTER_INDEX_MESSAGE = "Enter the index: ";
	public static final String ENTER_THE_MINIMUM_BALANCE = "Enter the minimum balance: ";

	public static final String INVALID_INDEX_MESSAGE = "Invalid Index! Please try again!";
	public static final String NO_AVAILABLE_MANAGERS_MESSAGE = "No Available Managers!";
	public static final String INVALID_SWITCH_CASE_INPUT = "Invalid Choice";
	public static final String FAILED_CUSTOMER_ACCOUNT_CREATION = "Failed to create customer bank account.";
	public static final String SUCCESSFUL_CUSTOMER_ACCOUNT_CREATION = "Customer Account Created Successfully!";
	public static final String AVAILABLE_BRANCHES_MESSAGE = "Here are all the available branches";
	public static final String NO_ISSUES_PRESENT_MESSAGE = "No Issues present!";
	public static final String ENTER_ROLE = "Enter role (CUSTOMER, BRANCH_MANAGER): ";
	public static final String ENTER_ADDRESS = "Enter address: ";
	public static final String ENTER_PHONE_NUMBER = "Enter phone number: ";
	public static final String ENTER_GENDER = "Enter gender (M/F/O): ";
	public static final String ENTER_A_VALID_AGE = "Enter a valid age!";
	public static final String ENTER_AGE = "Enter age: ";
	public static final String PASSWORD_NOT_STRONG_ENOUGH = "Password not strong enough!";
	public static final String ENTER_PASSWORD = "NOTE: Password must contain atleast 8 characters consisting of Uppercase, Lowercase, Digits and Special Characters.\nEnter password: ";
	public static final String ENTER_USERNAME = "Enter username: ";
	public static final String ENTER_EMAIL = "Enter email: ";
	public static final String ENTER_NAME = "Enter name: ";
	public static final String ENTER_YOUR_ISSUE = "Enter Your Issue: ";
	public static final String ACCOUNT_CLOSED_SUCCESSFULLY = "Account Closed Successfully!";
	public static final String ENTER_MAXIMUM_AMOUNT_TO_FILTER_BY = "Enter Maximum Amount to filter by: ";
	public static final String ENTER_MINIMUM_AMOUNT_TO_FILTER_BY = "Enter Minimum Amount to filter by: ";
	
	
	public static final String ENTER_AMOUNT_TO_TRANSFER = "Enter amount to Transfer: ";
	public static final String ENTER_ACCOUNT_NUMBER_OF_RECEIVER = "Enter Account Number of the receiver: ";
	public static final String ENTER_AMOUNT_TO_DEPOSIT = "Enter amount to Deposit: ";
	public static final String INVALID_AMOUNT = "Invalid amount! Please try again!";
	public static final String ENTER_AMOUNT_TO_WITHDRAW = "Enter amount to Withdraw: ";
	public static final String USER_UPDATION_UNSUCCESSFUL = "User updation Unsuccessful!";
	public static final String USER_UPDATION_SUCCESS = "User Updated Successfully!";
	public static final String WELCOME_TO_MANAGER_PORTAL = "Welcome to Manager Portal ! ";
	public static final String ENTER_THE_MAXIMUM_BALANCE = "Enter the maximum balance: ";
	public static final String NO_ISSUES_PRESENT = "No Issues present!";
	
	

	public static final String ENTER_ACCOUNT_NUMBER = "Enter Account Number: ";
	public static final String ACCOUNT_UPDATION_SUCCESSFUL = "Account updated successfully.";
	public static final String SELECT_AN_OPTION = "Select an option: ";
	public static final String SELECT_NEW_ACCOUNT_OWNER = "Select New Account Owner: ";
	public static final String ACCOUNT_NOT_FOUND = "Account not found.";
	public static final String SELECT_ACCOUNT_TO_UPDATE = "Select Account to Update: ";
	public static final String ERROR_RETRIEVING_ACCOUNT = "Error retrieving Account: ";
	public static final String INVALID_USERNAME = "Invalid Username";
//	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static final String BRANCH_CAN_T_BE_DELETED_MESSAGE = "Branch can't be deleted. First move all accounts to some other branch!";
	public static final String BRANCH_DELETED_SUCCESSFULLY = "Branch deleted successfully.";
	public static final String THERE_ARE_NO_BRANCHES = "There are no branches present.";
	public static final String BRANCH_UPDATED_SUCCESSFULLY = "branch updated successfully.";
	public static final String ENTER_NEW_BRANCH_ADDRESS = "Enter new branch Address: ";
	public static final String ENTER_NEW_BRANCH_NAME = "Enter new branch name: ";
	public static final String BRANCH_NOT_FOUND = "Branch not found.";
	public static final String ENTER_BRANCH_ID = "Enter branch ID: ";
	public static final String ERROR_DELETING_BRANCH = "Error deleting branch: ";
	public static final String ERROR_UPDATING_BRANCH = "Error updating branch: ";
	public static final String ERROR_RETRIEVING_BRANCH = "Error retrieving branch: ";
	
	public static final String ISSUE_ALREADY_RESOLVED = "This issue has already been resolved!";
	public static final String BRANCH_CAN_T_BE_NULL = "Branch can't be null";
	public static final String ERROR_RETRIEVING_ISSUE = "Error retrieving Issue: ";
	public static final String ENTER_ISSUE_ID = "Enter Issue ID: ";
	public static final String ISSUE_COULD_NOT_BE_RAISED = "Issue could not be raised.";
	public static final String ISSUE_RAISED_SUCCESSFULLY = "Issue raised successfully.";

	public static final String USER_AUTHENTICATION_FAILED_USERNAME = "User Authentication Failed!! \n Username: ";
	public static final String USER_AUTHENTICATED_SUCCESSFULLY_USERNAME = "User Authenticated Successfully !\nUsername: ";
	public static final String INVALID_CREDENTIALS = "Invalid Credentials!";
	public static final String FAILED_LOGIN_ATTEMPT_ACCOUNT_ALREADY_CLOSED_USERNAME = "Failed Login Attempt! Account already closed!\n Username: ";
	public static final String ACCOUNT_ALREADY_CLOSED = "This account has been closed!";
	public static final String CREDENTIALS_CAN_T_BE_EMPTY = "Credentials can't be empty";

	public static final String ERROR_RETRIEVING_NOTIFICATIONS = "Error retrieving Notifications: ";
    public static final String SELECT_THE_RECEIVER = "Select the Receiver: ";
    public static final String ENTER_TYPE_OF_NOTIFICATION = "Enter Type of Notification - SYSTEM_ALERT or ACCOUNT_ACTIVITY: ";
    public static final String ENTER_NOTIFICATION_MESSAGE = "Enter Message: ";
    
    public static final String ERROR_UPDATING_USER = "Error updating user: ";
    public static final String ERROR_DELETING_USER = "Error deleting user: ";
    public static final String ERROR_RETRIEVING_USER = "Error retrieving user: ";
    public static final String USER_NOT_FOUND = "User not found.";
    public static final String ERROR_ADDING_USER = "Error adding user: ";
    
    public static final String ACCOUNT_HAS_ALREADY_BEEN_CLOSED = "Account has already been closed";
    public static final String ADMIN_ALREADY_EXISTS = "Admin already exists!";
    public static final String ADMIN_CANNOT_BE_DELETED = "Admin Cannot be deleted";

    public static final String INSUFFICIENT_BALANCE = "Insufficient balance ";
	public static final String TARGET_ACCOUNT_BALANCE_UPDATED = "Target Account Balance Updated";
	public static final String SOURCE_ACCOUNT_BALANCE_UPDATED = "Source Account Balance Updated";
	public static final String CAN_T_TRANSFER_TO_SAME_ACCOUNT = "Can't Transfer to same account!";
	public static final String ACCOUNT_BALANCE_UPDATED = "Account Balance Updated!";
	public static final String NO_ACCOUNT_EXISTS_WITH_ACCOUNT_NUMBER = "No account exists with Account Number = ";
	public static final String NOT_ENOUGH_FUNDS = "Not Enough Funds !";
	
	public static final String ROLE_CANNOT_BE_UPDATED = "Role cannot be updated!";
}