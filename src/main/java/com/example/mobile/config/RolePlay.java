package com.example.mobile.config;

public enum RolePlay {
    USER("USER"),
    ADMIN("ADMIN"),
    SHIPPER("SHIPPER"),
    SHOP_OWNER("SHOP_OWNER");

    private final String role;

    RolePlay(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }
}
