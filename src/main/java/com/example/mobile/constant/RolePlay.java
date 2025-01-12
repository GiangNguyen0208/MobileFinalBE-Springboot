package com.example.mobile.constant;

public enum RolePlay {
    USER("USER"),
    ADMIN("ADMIN"),
    SHIPPER("SHIPPER"),
    SHOP("Shop");

    private final String role;

    RolePlay(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }
}
