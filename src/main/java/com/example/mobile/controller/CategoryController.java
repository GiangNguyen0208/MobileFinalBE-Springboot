package com.example.mobile.controller;

import com.example.mobile.dto.request.CategoryCreationReq;
import com.example.mobile.dto.request.CategoryUpdateReq;
import com.example.mobile.dto.response.ApiResponse;
import com.example.mobile.dto.response.CategoryResponse;
import com.example.mobile.service.imp.ICategory;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
    ICategory categoryService;

    @PostMapping("/add")
    ApiResponse<CategoryResponse> addCategory(@RequestBody @Valid CategoryCreationReq categoryCreationReq) {
        ApiResponse<CategoryResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(categoryService.addCategory(categoryCreationReq));
        return apiResponse;
    }
    @GetMapping("/listCategory")
    ApiResponse<List<CategoryResponse>> getListCategory() {
        return ApiResponse.<List<CategoryResponse>>builder()
                .result(categoryService.getListCategory())
                .build();
    }


    @GetMapping("/findId/{categoryId}")
    CategoryResponse CategoryResponse(@PathVariable("categoryId") int categoryId) {
        return categoryService.findCategoryById(categoryId);
    }

    @GetMapping("/findName/{categoryName}")
    CategoryResponse CategoryResponse(@PathVariable("categoryName") String name) {
        return categoryService.findCategoryByName(name);
    }

    @PutMapping("/update/{categoryId}")
    ApiResponse<CategoryResponse> updateCategory(@PathVariable("categoryId") int categoryId, @RequestBody CategoryUpdateReq categoryUpdateReq) {
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.categoryUpdate(categoryId,categoryUpdateReq)).build();
    }

    @DeleteMapping("/delete/{categoryId}")
    String deleteCategory(@PathVariable("categoryId") int categoryId) {
        categoryService.deleteCategory(categoryId);
        return "Category has been deleted!";
    }

}
