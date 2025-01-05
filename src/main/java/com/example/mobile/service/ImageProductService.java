package com.example.mobile.service;

import com.example.mobile.configuration.ImageUpload;
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
    public String uploadImage(MultipartFile file, int productId) throws IOException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found!"));

        ImageProduct imageProduct = imageProductRepository.save(
                ImageProduct.builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .linkImage(ImageUpload.compressImage(file.getBytes()))
                        .product(product)
                        .build()
        );
        return imageProduct != null ? "Upload Image Success: " + file.getOriginalFilename() : "Upload Image Fail";
    }

    @Override
    public byte[] downloadImage(String filename) {
        ImageProduct imageProduct = imageProductRepository.findByName(filename)
                .orElseThrow(() -> new RuntimeException("Image not found with filename: " + filename));
        return ImageUpload.decompressImage(imageProduct.getLinkImage());
    }

    @Override
    public List<byte[]> showProductImage(int idProduct) {
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + idProduct));
        List<ImageProduct> images = imageProductRepository.findAllByProduct(product);

        return images.stream()
                .map(imageProduct -> ImageUpload.decompressImage(imageProduct.getLinkImage()))
                .toList();
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
