package com.ivarrace.gringotts.application.rest.dto;

import org.apache.commons.lang3.StringUtils;

public class DtoUtils {

    private static final int MAX_KEY_LENGTH = 20;

    private DtoUtils() {
    }

    public static String generateKey(String str) {
        if (str == null || str.isEmpty()) {
            return "empty";
        }
        return StringUtils.stripAccents(str.replaceAll("\\s", "_")
                .toLowerCase()
                .substring(0, Math.min(MAX_KEY_LENGTH, str.length())));
    }

    public static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
