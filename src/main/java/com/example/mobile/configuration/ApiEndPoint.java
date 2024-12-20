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

    };

    public static final String[] PUBLIC_ENDPOINTS = {
            "/api/v1/users",
            "/api/v1/auth/token",
            "/api/v1/auth/introspect",
            "/api/v1/orders/listOrder",
            "/api/v1/carts/orderDetail",
            "/api/v1/admin/listShop",
            "/api/v1/admin/addShop"

    };
}
