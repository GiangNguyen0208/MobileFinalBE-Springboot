package com.example.mobile.controller;

import com.example.mobile.dto.response.ApiResponse;
import com.example.mobile.dto.response.CategoryResponse;
import com.example.mobile.dto.response.ProductResponse;
import com.example.mobile.dto.response.ProductWithShop;
import com.example.mobile.entity.Category;
import com.example.mobile.service.imp.ICategory;
import com.example.mobile.service.imp.IProduct;
import com.example.mobile.service.imp.IShop;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/shop")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ShopController {
    ICategory categoryService;
    IProduct productService;
    IShop shopService;

    @GetMapping("/{shopId}/categories")
    ApiResponse<List<CategoryResponse>> getCategoryList(@PathVariable("shopId") int shopId) {
        return ApiResponse.<List<CategoryResponse>>builder()
                .mesg("Get List Category")
                .result(categoryService.getListCategoryByShopId(shopId))
                .build();
    }
    @GetMapping("/{categoryId}/listProduct")
    ApiResponse<List<ProductResponse>> getProductList(@PathVariable("categoryId") int id) {
        return ApiResponse.<List<ProductResponse>>builder()
                .mesg("Get List Product")
                .result(productService.getListProductByCategory(id))
                .build();
    }
    @GetMapping("/{shopId}/products")
    public ApiResponse<List<ProductResponse>> getProductListByShopID(@PathVariable("shopId") int shopId) {
        log.info("Fetching products for shop: " + shopId); // Thêm log để kiểm tra
        List<ProductResponse> products = productService.getListProductByShopID(shopId);
        return ApiResponse.<List<ProductResponse>>builder()
                .mesg("Get List Product By Shop ID")
                .result(products)
                .build();
    }
    @GetMapping("/open")
    public ApiResponse<List<Integer>> getOpenShopIds() {
        List<Integer> openShopIds = shopService.getOpenShopIds();
        return ApiResponse.<List<Integer>>builder()
                .mesg("List of Open Shops")
                .result(openShopIds)
                .build();
    }

}
