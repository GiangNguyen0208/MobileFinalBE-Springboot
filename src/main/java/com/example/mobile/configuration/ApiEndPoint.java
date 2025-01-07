package com.example.mobile.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiEndPoint {
    public static final String[] ADMIN_ENDPOINTS = {
            "/api/v1/users/listUser",


            // product crud
            "/api/v1/products/add",
            "/api/v1/products/listProduct",
            "/api/v1/products/{productId}",
            "/api/v1/products/listProductByCategory",

            //voucher crud
            "/api/v1/vouchers/add",
            "/api/v1/vouchers/listVoucher",
            "/api/v1/vouchers/{voucherId}",


            // search product
            "/api/v1/products/{productName}",


            // admin/shop
            "/api/v1/admin/shop/add",
            "/api/v1/admin/shop/list",
            "/api/v1/admin/shop/id/{shopId}",
            "/api/v1/admin/shop/name/{shopName}",
            "/api/v1/admin/shop/update/{shopId}",
            "/api/v1/admin/shop/delete/{shopId}",

            // admin/user

            "/api/v1/admin/user/add",
            "/api/v1/admin/user/list",
            "/api/v1/admin/user/id/{userId}",
            "/api/v1/admin/user/name/{userName}",
            "/api/v1/admin/user/update/{userId}",
            "/api/v1/admin/user/delete/{userId}",
    };

    public static final String[] PUBLIC_ENDPOINTS = {
            "/api/v1/users",
            "/api/v1/auth/token",
            "/api/v1/auth/introspect",
            "/api/v1/orders/listOrder",
            "/api/v1/carts/orderDetail",
            "/api/v1/admin/listShop",
            "/api/v1/admin/addShop"

    public static final String[] PUBLIC_DELETE_ENDPOINTS = {
            "/api/v1/roles/{role}",
            "/api/v1/product/images/{productId}",
    };
}
