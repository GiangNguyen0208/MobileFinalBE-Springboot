package com.example.mobile.constant;

public enum FoodStatus {
    SOLD_OUT("SOLD_OUT"),
    ON_SALE("ON_SALE");
    private final String status;

    FoodStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
