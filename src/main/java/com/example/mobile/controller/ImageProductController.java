package com.example.mobile.controller;

import com.example.mobile.dto.response.ApiResponse;
import com.example.mobile.service.imp.IImageProduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product/images")
@RequiredArgsConstructor
@Slf4j
public class ImageProductController {
    private final IImageProduct imageProductService;
    @PostMapping("/upload/{productId}")
    public ApiResponse<String> uploadImageProduct(
            @RequestParam("image") MultipartFile file,
            @PathVariable("productId") int id) throws IOException {
        if (file.isEmpty()) {
            throw new RuntimeException("File is empty!");
        }
        // Gọi service upload
        String uploadImage = imageProductService.uploadImage(file, id);
        return ApiResponse.<String>builder()
                .mesg("Upload Image Status")
                .result(uploadImage)
                .build();
    }


    // Get List Image Product
    @GetMapping("/show-list/{productId}")
    ApiResponse<List<byte[]>> downloadImageProduct(@PathVariable("productId") int productId) {
        List<byte[]> imageData = imageProductService.showProductImage(productId);
        return ApiResponse.<List<byte[]>>builder()
                .mesg("Show List Image Product")
                .result(imageData)
                .build();
    }

    // Get Image Product
    @GetMapping("/show/{filename}")
    ApiResponse<byte[]> downloadImageProduct(@PathVariable("filename") String filename) {
        byte[] imageData = imageProductService.downloadImage(filename);
        return ApiResponse.<byte[]>builder()
                .mesg("Show Image Product")
                .result(imageData)
                .build();
    }

    // Delete
    @DeleteMapping("/{idImage}")
    ApiResponse<Void> delete(@PathVariable("idImage") int idImage) {
        imageProductService.remove(idImage);
        return ApiResponse.<Void>builder()
                .mesg("Delete Image Product Success")
                .build();
    }
}
