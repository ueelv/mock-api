package com.decta.mockapi.utils;

import java.util.UUID;

public class UId {
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
