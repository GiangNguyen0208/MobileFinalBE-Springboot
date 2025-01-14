package com.example.mobile.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiEndPoint {

    public static final String[] ADMIN_GET_ENDPOINTS = {
            "/api/v1/users/listUser",

            // product crud
            "/api/v1/products/listProduct",
            "/api/v1/products/{productId}",
            "/api/v1/products/listProductByCategory",

            //voucher crud
            "/api/v1/vouchers/add",
            "/api/v1/vouchers/listVoucher",
            "/api/v1/vouchers/{voucherId}",



            // Get List User
            "/api/v1/users/getAllUser",
            "/api/v1/admin/user/getAllUser",
            "/api/v1/admin/user/id/{userId}",
            "/api/v1/categories/add",
            "/api/v1/categories/update/{categoryId}",
            "/api/v1/categories/delete/{categoryId}",

            "/api/v1/notifications/add",
            "/api/v1/notifications/update/{notificationId}",
            "/api/v1/notifications/delete/{notificationId}",

            "/api/v1/admin/shop/add", "/api/v1/admin/shop/list", "/api/v1/admin/shop/id/{shopId}", "/api/v1/admin/shop/name/{shopName}", "/api/v1/admin/shop/update/{shopId}", "/api/v1/admin/shop/delete/{shopId}",
            // admin/user
            "/api/v1/admin/user/add", "/api/v1/admin/user/list", "/api/v1/admin/user/id/{userId}", "/api/v1/admin/user/name/{userName}", "/api/v1/admin/user/update/{userId}", "/api/v1/admin/user/delete/{userId}",



    };

    public static final String[] ADMIN_POST_ENDPOINTS = {
            //Role
//            "/api/v1/roles/create",



    };

    public static final String[] PUBLIC_POST_ENDPOINTS  = {
            // search product
            "/api/v1/products/{productName}",

            // Login vs Register
            "/api/v1/users/register",
            "/api/v1/auth/token",
            "/api/v1/auth/introspect",
            "/api/v1/location/save-location",
            "/api/v1/shop/uploadData",

            "/api/v1/orders/listOrder",
            "/api/v1/carts/orderDetail",
            "/api/v1/admin/listShop",
            "/api/v1/admin/addShop",

            // Add, Deleted Cart
            "/api/v1/cart/add/",
            "/api/v1/cart/clear/",

            // Permission
            "/api/v1/permissions/create",



    };

    public static final String[] PUBLIC_GET_ENDPOINTS = {
            "/api/v1/cart/view",
            "/api/v1/cart/increase/{productId}",
            "/api/v1/cart/decrease/{productId}",

            // Permission
            "/api/v1/permissions/getAll",

            // Role
            "/api/v1/roles/getAll",

            // search product
            "/api/v1/products/{productId}",
            "/api/v1/products/findName/{productName}",
            "/api/v1/products/listProduct",
            "/api/v1/products/listProductByCategory",
            "/api/v1/products/getByCategory/{categoryId}",


            "/api/v1/vouchers/listVoucher",
            "/api/v1/vouchers/findId/{voucherId}",

            "/api/v1/categories/listCategory",
            "/api/v1/categories/findId/{categoryId}",
            "/api/v1/categories/findName/{categoryName}",

            "/api/v1/notifications/listNotification",
            "/api/v1/notifications/findId/{notificationId}",
            "/api/v1/notifications/findTitle/{notificationTitle}",
            "/api/v1/notifications/listNotification/{shopId}",

            "/api/v1/comments/add",
            "/api/v1/comments/delete/{commentId}",
            "/api/v1/comments/update/{commentId}",
            "/api/v1/comments/list/shop/{shopId}",
            "/api/v1/comments/list/user/{userId}",
            "/api/v1/comments/list/product/{productId}",

    };

    public static final String[] PUBLIC_DELETE_ENDPOINTS = {
            "/api/v1/roles/{role}",
            "/api/v1/product/images/{productId}"
    };

    public static final String[] SHOP_POST_ENDPOINTS = {
            //Role
            "/api/v1/products/add",

            // Product upload image
            "/api/v1/product/images/upload/*",
    };
    public static final String[] SHOP_GET_ENDPOINTS = {
            // Product
            "/api/v1/shop/{shopId}/products",
            "/api/v1/products/{productId}",           // Cập nhật sản phẩm

            // Category
            "/api/v1/shop/{shopID}/categories",      // Lấy danh sách category của shop
            "/api/v1/categories/{categoryId}",

            // Image
            "/api/v1/product/images/show-list/{productId}",  // Lấy danh sách hình ảnh của sản phẩm
            "/api/v1/product/images/show/{filename}",       // Lấy hình ảnh của sản phẩm theo tên file
    };

    public static final String[] SHOP_DELETE_ENDPOINTS = {
            "/api/v1/product/images/{idImage}",
            "/api/v1/products/{productId}",
    };

}
