package com.ebs.biocrop.entity.enums;

public enum ShippingMethod {
    COURIER("Shipping Through Courier"),
    TRANSPORT("Shipping Through Transport");

    private final String description;

    ShippingMethod(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static ShippingMethod fromString(String method) {
        if (method == null || method.isBlank()) {
            return COURIER;
        }
        for (ShippingMethod sm : values()) {
            if (sm.name().equalsIgnoreCase(method.trim()) || sm.description.equalsIgnoreCase(method.trim())) {
                return sm;
            }
        }
        return COURIER;
    }
}
