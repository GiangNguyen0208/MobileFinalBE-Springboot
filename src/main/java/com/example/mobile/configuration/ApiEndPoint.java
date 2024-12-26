package com.example.mobile.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiEndPoint {
    public static final String[] ADMIN_ENDPOINTS = {
            "/api/v1/users/listUser",

    };

    public static final String[] PUBLIC_ENDPOINTS = {
            "/api/v1/users",
            "/api/v1/auth/token",
            "/api/v1/auth/introspect",
            "/api/v1/users/create",
            "/api/v1/carts/add-to-cart",
            "/api/v1/location/save-location",
            "/api/v1/shop/uploadData",
            "/api/v1/comments/comment",
            "/api/v1/comments/show-all-comment",
            "/api/v1/shop/add-shop",
            "/api/v1/shop/update-information-shop",
            "/api/v1/shop/delete-shop",
    };
}
