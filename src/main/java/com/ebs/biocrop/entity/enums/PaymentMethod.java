package com.ebs.biocrop.entity.enums;

public enum PaymentMethod {
    ONLINE("Online"),
    COD("Cod"),
    BOTH("Both");

    private final String displayValue;

    PaymentMethod(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public static PaymentMethod fromString(String method) {
        if (method == null || method.isBlank()) {
            return ONLINE;
        }
        for (PaymentMethod pm : values()) {
            if (pm.name().equalsIgnoreCase(method.trim()) || pm.displayValue.equalsIgnoreCase(method.trim())) {
                return pm;
            }
        }
        return ONLINE;
    }
}
