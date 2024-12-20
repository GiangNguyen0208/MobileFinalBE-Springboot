package com.example.mobile.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiEndPoint {
    public static final String[] ADMIN_ENDPOINTS = {
            "/api/v1/users/listUser",

            "/api/v1/products/add",
            "/api/v1/products/listProduct",
            "/api/v1/products/{productId}",

            "/api/v1/vouchers/add",
            "/api/v1/vouchers/listVoucher",
            "/api/v1/vouchers/{voucherId}",
    };

    public static final String[] PUBLIC_ENDPOINTS = {
            "/api/v1/users/create",
            "/api/v1/auth/token",
            "/api/v1/auth/introspect",
    };
}
