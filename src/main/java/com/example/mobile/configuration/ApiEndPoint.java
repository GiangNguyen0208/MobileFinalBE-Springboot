package com.example.mobile.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiEndPoint {
    public static final String[] ADMIN_GET_ENDPOINTS = {
            "/api/v1/users/listUser",


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

            // Shop upload image
//            "/api/v1/shop/images/upload/*",
    };

    public static final String[] SHOP_POST_ENDPOINTS = {
            //Role

            // Product upload image
            "/api/v1/product/images/upload/*",
    };
    public static final String[] SHOP_GET_ENDPOINTS = {
            // Product

            "/api/v1/products/{productId}",           // Cập nhật sản phẩm

            // Category
            "/api/v1/shop/{shopID}/categories",      // Lấy danh sách category của shop

            // Image
            "/api/v1/product/images/show-list/{productId}",  // Lấy danh sách hình ảnh của sản phẩm
            "/api/v1/product/images/show/{filename}",       // Lấy hình ảnh của sản phẩm theo tên file
    };

    public static final String[] SHOP_DELETE_ENDPOINTS = {
            "/api/v1/product/images/{idImage}"
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
            "/api/v1/roles",
    };
    public static final String[] PUBLIC_GET_ENDPOINTS = {
            // Category
            "/api/v1/categories/list",


            // Product
            "/api/v1/products/listProduct",

            // Cart
            "/api/v1/cart/view",
            "/api/v1/cart/increase/{productId}",
            "/api/v1/cart/decrease/{productId}",

            // Permission
            "/api/v1/permissions/getAll",

            // Image Product Show
            "/api/v1/product/images/show-list/*",   // Show List Image
            "/api/v1/product/images/show/*",    // Show one Image

            // product
            "/api/v1/products/byId/*",  // Xem chi tiet
            "/api/v1/products/listProductByCategory",   // Lay danh sach theo category

    };

    public static final String[] PUBLIC_DELETE_ENDPOINTS = {
            "/api/v1/roles/{role}",
            "/api/v1/product/images/{productId}",
    };
}
