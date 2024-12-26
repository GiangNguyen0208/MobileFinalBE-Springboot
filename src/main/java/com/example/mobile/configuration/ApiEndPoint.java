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
            "/api/v1/products/add",
      
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


    };
}
