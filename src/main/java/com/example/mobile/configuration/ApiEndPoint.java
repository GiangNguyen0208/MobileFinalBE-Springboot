package com.example.mobile.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiEndPoint {
    public static final String[] ADMIN_ENDPOINTS = {
            "/api/v1/users/listUser",

            "/api/v1/products/add",
            "/api/v1/products/update/{productId}",
            "/api/v1/products/delete/{productId}",

            //voucher crud
            "/api/v1/vouchers/add",
            "/api/v1/vouchers/update/{voucherId}",
            "/api/v1/vouchers/delete/{voucherId}",
    };

    public static final String[] PUBLIC_ENDPOINTS = {
            "/api/v1/users/create",
            "/api/v1/auth/token",
            "/api/v1/auth/introspect",

            // product crud
            "/api/v1/products/listProduct",
            "/api/v1/products/findId/{productId}",
            "/api/v1/products/findName/{productName}",
            "/api/v1/products/listProductByCategory",


            "/api/v1/vouchers/listVoucher",
            "/api/v1/vouchers/findId/{voucherId}",


    };
}
