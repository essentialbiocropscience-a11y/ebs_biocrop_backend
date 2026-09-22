package com.ebs.biocrop.entity.enums;

public enum ProductStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive");

    private final String displayValue;

    ProductStatus(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public static ProductStatus fromString(String status) {
        if (status == null || status.isBlank()) {
            return ACTIVE;
        }
        for (ProductStatus ps : values()) {
            if (ps.name().equalsIgnoreCase(status.trim()) || ps.displayValue.equalsIgnoreCase(status.trim())) {
                return ps;
            }
        }
        return ACTIVE;
    }
}
