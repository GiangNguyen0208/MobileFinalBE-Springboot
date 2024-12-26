package com.example.mobile.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiEndPoint {
    public static final String[] ADMIN_GET_ENDPOINTS = {
            "/api/v1/users/listUser",


            "/api/v1/products/add",
            "/api/v1/products/update/{productId}",
            "/api/v1/products/delete/{productId}",


            //voucher crud
            "/api/v1/vouchers/add",
            "/api/v1/vouchers/update/{voucherId}",
            "/api/v1/vouchers/delete/{voucherId}",

            "/api/v1/categories/add",
            "/api/v1/categories/update/{categoryId}",
            "/api/v1/categories/delete/{categoryId}",


            "/api/v1/notifications/add",
            "/api/v1/notifications/update/{categoryId}",
            "/api/v1/notifications/delete/{categoryId}",
    };

    public static final String[] PUBLIC_ENDPOINTS = {
            "/api/v1/users",
            "/api/v1/users/findId/{userId}",
            "/api/v1/auth/token",
            "/api/v1/auth/introspect",


            // product crud
            "/api/v1/products/listProduct",
            "/api/v1/products/findId/{productId}",
            "/api/v1/products/findName/{productName}",
            "/api/v1/products/listProductByCategory",
            "/api/v1/products/listProduct/shop/{shopName}",


            "/api/v1/vouchers/listVoucher",
            "/api/v1/vouchers/findId/{voucherId}",

            "/api/v1/categories/listCategory",
            "/api/v1/categories/findId/{categoryId}",
            "/api/v1/categories/findName/{categoryName}",

            "/api/v1/notifications/listCategory",
            "/api/v1/notifications/listCategory/{shopId}",
            "/api/v1/notifications/findId/{categoryId}",
            "/api/v1/notifications/findTitle/{categoryTitle}",

            "/api/v1/orders/listOrder",
            "/api/v1/carts/orderDetail",
            "/api/v1/admin/listShop",
            "/api/v1/admin/addShop",


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
            "/api/v1/location/save-location",
            "/api/v1/shop/uploadData",


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
}
