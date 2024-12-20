package com.example.mobile.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiEndPoint {
    public static final String[] ADMIN_ENDPOINTS = {
            "/api/v1/users/getAllUser"
    };

    public static final String[] PUBLIC_POST_ENDPOINTS = {
            "/api/v1/users/register",
            "/api/v1/auth/token",
            "/api/v1/auth/introspect"
    };
}
