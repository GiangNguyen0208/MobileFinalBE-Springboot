package com.example.mobile.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiEndPoint {
    public static final String[] ADMIN_ENDPOINTS = {
            "/api/v1/users/listUser",

            // product crud
            "/api/v1/products/add",
            "/api/v1/products/update/{productId}",
            "/api/v1/products/delete/{productId}",


            //voucher crud
            "/api/v1/vouchers/add",
            "/api/v1/vouchers/update/{productId}",
            "/api/v1/vouchers/delete/{productId}",

            "/api/v1/categories/add",
            "/api/v1/categories/update/{categoryId}",
            "/api/v1/categories/delete/{categoryId}",

            "/api/v1/notifications/add",
            "/api/v1/notifications/update/{notificationId}",
            "/api/v1/notifications/delete/{notificationId}",

    };

    public static final String[] PUBLIC_ENDPOINTS = {
            "/api/v1/users",
            "/api/v1/auth/token",
            "/api/v1/auth/introspect",
            "/api/v1/location/save-location",
            "/api/v1/shop/uploadData",

            // search product
            "/api/v1/products/findId/{productId}",
            "/api/v1/products/findName/{productName}",
            "/api/v1/products/listProduct",
            "/api/v1/products/listProduct/shop/{shopName}",
            "/api/v1/products/listProductByCategory",


            "/api/v1/vouchers/listVoucher",
            "/api/v1/vouchers/findId/{voucherId}",

            "/api/v1/categories/listCategory",
            "/api/v1/categories/findId/{categoryId}",
            "/api/v1/categories/findName/{categoryName}",

            "/api/v1/notifications/listNotification",
            "/api/v1/notifications/findId/{notificationId}",
            "/api/v1/notifications/findTitle/{notificationTitle}",
            "/api/v1/notifications/listNotification/{shopId}",


            "/api/v1/orders/listOrder",
            "/api/v1/carts/orderDetail",
            "/api/v1/admin/listShop",
            "/api/v1/admin/addShop"
    };
}
