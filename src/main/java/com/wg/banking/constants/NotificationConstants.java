package com.wg.banking.constants;

public final class NotificationConstants {

    public static final String NOTIFICATION_TABLE_NAME = "Notification";
    public static final String NOTIFICATION_ID_COLUMN = "notification_id";
    public static final String MESSAGE_COLUMN = "message";
    public static final String NOTIFICATION_TYPE_COLUMN = "notification_type";
    public static final String RECEIVER_ID_COLUMN = "receiver_id";
    public static final String CREATED_AT_COLUMN = "createdAt";

    public static final String ACCOUNT_ACTIVITY_TYPE = "ACCOUNT_ACTIVITY";
    public static final String SYSTEM_ALERT_TYPE = "SYSTEM_ALERT";


    private NotificationConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
