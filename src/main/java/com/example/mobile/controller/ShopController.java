package com.example.mobile.controller;

import com.example.mobile.dto.response.ApiResponse;
import com.example.mobile.dto.response.CategoryResponse;
import com.example.mobile.entity.Category;
import com.example.mobile.service.imp.ICategory;
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

    @GetMapping("/{shopId}/categories")
    ApiResponse<List<CategoryResponse>> getCategoryList(@PathVariable("shopId") int shopId) {
        return ApiResponse.<List<CategoryResponse>>builder()
                .mesg("Get List Category")
                .result(categoryService.getListCategory(shopId))
                .build();
    }

//    @GetMapping("/listProduct/{categoryId}")
//    ApiResponse<List<ProductResponse>> getCategoryList(@PathVariable("shopID") int id) {
//        return ApiResponse.<List<Category>>builder()
//                .mesg("Get List Category")
//                .result(categoryService.getListCategory(id))
//                .build();
//    }

}
