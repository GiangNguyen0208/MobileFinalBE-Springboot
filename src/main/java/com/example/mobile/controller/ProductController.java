package com.example.mobile.controller;

import com.example.mobile.dto.request.ProductCreationReq;
import com.example.mobile.dto.request.ProductUpdateReq;
import com.example.mobile.dto.response.ApiResponse;
import com.example.mobile.dto.response.CategoryResponse;
import com.example.mobile.dto.response.ProductResponse;
import com.example.mobile.dto.response.ProductWithShop;
import com.example.mobile.service.imp.IProduct;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    IProduct productService;
    @PostMapping("/add")
    ApiResponse<Boolean> addProduct(@RequestBody ProductCreationReq productCreationReq) {
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();
        apiResponse.setMesg("ADD FOOD STATUS");
        apiResponse.setResult(productService.addProduct(productCreationReq));
        return apiResponse;
    }
    @GetMapping("/listProduct")
    ApiResponse<List<ProductResponse>> getListProduct() {
        return ApiResponse.<List<ProductResponse>>builder()
                .result(productService.getListProduct())
                .build();
    }

    @GetMapping("/getByCategory/{categoryId}")
    ApiResponse<List<ProductResponse>> getListProductByCategory(@PathVariable("categoryId") int categoryId) {
        return ApiResponse.<List<ProductResponse>>builder()
                .result(productService.getListProductByCategory(categoryId))
                .build();
    }

    @GetMapping("/{productId}")
    ApiResponse<ProductResponse> ProductResponse(@PathVariable("productId") int productId) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.findProductById(productId))
                .build();
    }
    @PutMapping("/{productId}")
    ProductResponse updateProduct(@PathVariable("productId") int productId, @RequestBody ProductUpdateReq productUpdateReq) {
        return productService.productUpdate(productId, productUpdateReq);
    }
    @DeleteMapping("/{productId}")
    ResponseEntity<Void> deleteProduct(@PathVariable("productId") int productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/listProduct/shop/{shopName}")
    ApiResponse<List<ProductWithShop>> getListByShopName(@PathVariable("shopName") String shopName) {
        return ApiResponse.<List<ProductWithShop>>builder()
                .result( productService.getListByShopName(shopName)).build();
    }

    @GetMapping("/findName/{productName}")
    ApiResponse<List<ProductResponse>> getListByByNameContains(@PathVariable("productName") String productName) {
        return ApiResponse.<List<ProductResponse>>builder()
                .result( productService.findAllByNameContains(productName)).build();
    }
}
