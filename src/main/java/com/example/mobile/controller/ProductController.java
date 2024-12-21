package com.example.mobile.controller;

import com.example.mobile.dto.request.ProductCreationReq;
import com.example.mobile.dto.request.ProductUpdateReq;
import com.example.mobile.dto.response.ApiResponse;
import com.example.mobile.dto.response.ProductResponse;
import com.example.mobile.service.imp.IProduct;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    IProduct productService;
    @PostMapping("/add")
    ApiResponse<ProductResponse> addProduct(@RequestBody @Valid ProductCreationReq productCreationReq) {
        System.out.println("Request payload: " + productCreationReq);
        ApiResponse<ProductResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(productService.addProduct(productCreationReq));
        return apiResponse;
    }
    @GetMapping("/listProduct")
    ApiResponse<List<ProductResponse>> getListProduct() {
        return ApiResponse.<List<ProductResponse>>builder()
                .result(productService.getListProduct())
                .build();
    }

    @GetMapping("/listProductByCategory")
    ApiResponse<List<ProductResponse>> getListProductByCategory() {
        return ApiResponse.<List<ProductResponse>>builder()
                .result(productService.getListProductByCategory())
                .build();
    }

    @GetMapping("/findId/{productId}")
    ProductResponse ProductResponse(@PathVariable("productId") int productId) {
        return productService.findProductById(productId);
    }

    @GetMapping("/findName/{productName}")
    ProductResponse ProductResponse(@PathVariable("productName") String name) {
        return productService.findProductByName(name);
    }

    @PutMapping("/update/{productId}")
    ApiResponse<ProductResponse> updateProduct(@PathVariable("productId") int productId, @RequestBody ProductUpdateReq productUpdateReq) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.productUpdate(productId,productUpdateReq)).build();
    }

    @DeleteMapping("/delete/{productId}")
    String deleteProduct(@PathVariable("productId") int productId) {
        productService.deleteProduct(productId);
        return "Product has been deleted!";
    }

}
