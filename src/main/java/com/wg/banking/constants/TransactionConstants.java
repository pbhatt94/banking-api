package com.wg.banking.constants;

public final class TransactionConstants {

    public static final String TRANSACTION_TABLE_NAME = "Transaction";
    public static final String TRANSACTION_ID_COLUMN = "transaction_id";
    public static final String AMOUNT_COLUMN = "amount";
    public static final String TRANSACTION_TYPE_COLUMN = "transaction_type";
    public static final String SOURCE_ACCOUNT_ID_COLUMN = "source_account_id";
    public static final String DESTINATION_ACCOUNT_ID_COLUMN = "destination_account_id";
    public static final String CREATED_AT_COLUMN = "createdAt";

    public static final String DEPOSIT_TYPE = "DEPOSIT";
    public static final String WITHDRAWAL_TYPE = "WITHDRAWAL";
    public static final String TRANSFER_TYPE = "TRANSFER";
    
    public static final String WITHDRAWAL_LOG = "";

    private TransactionConstants() {
    	throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
