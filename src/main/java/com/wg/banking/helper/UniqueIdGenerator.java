package com.wg.banking.helper;

import java.util.UUID;

public class UniqueIdGenerator {
    public static String generateUniqueId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().substring(24);
    }
}