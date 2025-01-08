package com.example.mobile.controller;

import com.example.mobile.dto.response.ApiResponse;
import com.example.mobile.dto.response.CategoryResponse;
import com.example.mobile.dto.response.ProductResponse;
import com.example.mobile.entity.Category;
import com.example.mobile.service.imp.ICategory;
import com.example.mobile.service.imp.IProduct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shop")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShopController {
    ICategory categoryService;
    IProduct productService;

    @GetMapping("/{shopId}/categories")
    ApiResponse<List<CategoryResponse>> getCategoryList(@PathVariable("shopId") int shopId) {
        return ApiResponse.<List<CategoryResponse>>builder()
                .mesg("Get List Category")
                .result(categoryService.getListCategoryBýhopId(shopId))
                .build();
    }
    @GetMapping("/{categoryId}/listProduct/")
    ApiResponse<List<ProductResponse>> getProductList(@PathVariable("categoryId") int id) {
        return ApiResponse.<List<ProductResponse>>builder()
                .mesg("Get List Product")
                .result(productService.getListProductByCategory(id))
                .build();
    }

}