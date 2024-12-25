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

//            "/api/v1/ratings/add",
//            "/api/v1/ratings/delete/{ratingId}",
//            "/api/v1/ratings/update/{productId}",
//            "/api/v1/ratings/findId/{ratingId}",
//            "/api/v1/ratings/listRating/user/{userId}",
//            "/api/v1/ratings/listRating/product/{productId}",
//            "/api/v1/ratings/listRating/shop/{shopId}",

    };
}
