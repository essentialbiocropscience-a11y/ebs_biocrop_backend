package com.ebs.biocrop.entity.enums;

public enum ProductUnit {
    LITER("liter"),
    ML("ml"),
    KG("kg"),
    GM("gm");

    private final String unitName;

    ProductUnit(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitName() {
        return unitName;
    }

    public static ProductUnit fromString(String unit) {
        if (unit == null || unit.isBlank()) {
            return LITER;
        }
        for (ProductUnit pu : values()) {
            if (pu.name().equalsIgnoreCase(unit.trim()) || pu.unitName.equalsIgnoreCase(unit.trim())) {
                return pu;
            }
        }
        return LITER;
    }
}
