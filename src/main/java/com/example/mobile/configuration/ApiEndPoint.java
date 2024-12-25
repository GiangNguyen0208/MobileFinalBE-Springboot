package com.example.mobile.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiEndPoint {
    public static final String[] ADMIN_GET_ENDPOINTS = {
            "/api/v1/users/listUser",

            // product crud
            "/api/v1/products/byId/*",
            "/api/v1/products/listProduct",
            "/api/v1/products/{productId}",
            "/api/v1/products/listProductByCategory",

            //voucher crud
            "/api/v1/vouchers/add",
            "/api/v1/vouchers/listVoucher",
            "/api/v1/vouchers/{voucherId}",


            // search product
            "/api/v1/products/{productName}",

            // Get List User
            "/api/v1/users/getAllUser",




    };

    public static final String[] ADMIN_POST_ENDPOINTS = {
            //Role
//            "/api/v1/roles/create",

    };

    public static final String[] PUBLIC_POST_ENDPOINTS = {
            // Login vs Register
            "/api/v1/users/register",
            "/api/v1/auth/token",
            "/api/v1/auth/introspect",

            // Add, Deleted Cart
            "/api/v1/cart/add/",
            "/api/v1/cart/clear/",

            // Permission
            "/api/v1/permissions/create",

            // Role
    };
    public static final String[] PUBLIC_GET_ENDPOINTS = {
            "/api/v1/cart/view",
            "/api/v1/cart/increase/{productId}",
            "/api/v1/cart/decrease/{productId}",

            // Permission
            "/api/v1/permissions/getAll",

            // Role
            "/api/v1/roles/getAll",
    };
    public static final String[] PUBLIC_DELETE_ENDPOINTS = {
            // Permission
            "/api/v1/permissions/{permissionId}",

            // Role
            "/api/v1/roles/{roleId}",

    };

}
