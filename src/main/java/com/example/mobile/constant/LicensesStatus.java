package com.example.mobile.constant;

public enum LicensesStatus {
    VALID("VALID"),
    INVALID("INVALID");

    private final String status;

    LicensesStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
