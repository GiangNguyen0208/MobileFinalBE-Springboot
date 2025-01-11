package com.example.mobile.service;

import com.example.mobile.configuration.ImageUpload;
import com.example.mobile.dto.response.ImageProductResponse;
import com.example.mobile.entity.ImageProduct;
import com.example.mobile.entity.Product;
import com.example.mobile.mapper.IProductMapper;
import com.example.mobile.repository.ImageProductRepository;
import com.example.mobile.repository.ProductRepository;
import com.example.mobile.service.imp.IImageProduct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ImageProductService implements IImageProduct {

    ImageProductRepository imageProductRepository;
    IProductMapper productMapper;
    ProductRepository productRepository;

    @Override
    public List<String> uploadImage(List<String> images, int productId) throws IOException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found!"));
        List<String> uploadedImages = new ArrayList<>();
        for (String image : images) {
            ImageProduct imageProduct = ImageProduct.builder()
                    .linkImage(image)
                    .product(product)
                    .build();
            imageProductRepository.save(imageProduct);
            uploadedImages.add(image);
        }
        return uploadedImages;
    }





//    @Override
//    public String downloadImage(String filename) {
//        ImageProduct imageProduct = imageProductRepository.findByName(filename)
//                .orElseThrow(() -> new RuntimeException("Image not found with filename: " + filename));
//        return ImageUpload.decompressImage(imageProduct.getLinkImage());
//    }

    @Override
    public List<String> showProductImage(int idProduct) {
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + idProduct));
        List<ImageProduct> images = imageProductRepository.findAllImagesByProductId(idProduct);
        List<String> imagesResponse = new ArrayList<>();

        for (ImageProduct imageProduct : images) {
            imagesResponse.add(imageProduct.getLinkImage());
        }
        return imagesResponse;
    }


    @Override
    public void remove(int id) {
        Optional<ImageProduct> imageProduct = imageProductRepository.findById(id);

        if (imageProduct.isPresent()) {
            imageProductRepository.deleteById(id);
            log.info("Image with ID {} has been removed successfully.", id);
        } else {
            log.error("Image with ID {} not found.", id);
            throw new RuntimeException("Image not found!");
        }
    }
}
