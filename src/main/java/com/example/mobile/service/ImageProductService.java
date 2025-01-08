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
    public List<String> uploadImage(List<MultipartFile> files, int productId) throws IOException {
        // Kiểm tra xem sản phẩm có tồn tại hay không
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found!"));

        // Duyệt qua danh sách file và lưu từng ảnh
        List<String> uploadedImages = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                throw new RuntimeException("File is empty: " + file.getOriginalFilename());
            }

            // Lưu ảnh vào cơ sở dữ liệu
            ImageProduct imageProduct = imageProductRepository.save(
                    ImageProduct.builder()
                            .name(file.getOriginalFilename())
                            .type(file.getContentType())
                            .linkImage(ImageUpload.compressImage(file.getBytes()))
                            .product(product)
                            .build()
            );

            // Thêm kết quả vào danh sách
            if (imageProduct != null) {
                uploadedImages.add("Uploaded: " + file.getOriginalFilename());
            } else {
                uploadedImages.add("Failed to upload: " + file.getOriginalFilename());
            }
        }

        return uploadedImages;
    }


    @Override
    public byte[] downloadImage(String filename) {
        ImageProduct imageProduct = imageProductRepository.findByName(filename)
                .orElseThrow(() -> new RuntimeException("Image not found with filename: " + filename));
        return ImageUpload.decompressImage(imageProduct.getLinkImage());
    }

    @Override
    public List<ImageProductResponse> showProductImage(int idProduct) {
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + idProduct));
        List<ImageProduct> images = imageProductRepository.findAllByProduct(product);
        List<ImageProductResponse> listResponseImages = new ArrayList<>();

        for (ImageProduct imageProduct : images) {
            byte[] decompressedImage = ImageUpload.decompressImage(imageProduct.getLinkImage());

            // Chuyển đổi ảnh đã giải nén thành Base64
            String base64Image = Base64.getEncoder().encodeToString(decompressedImage);

            ImageProductResponse imageProductResponse = ImageProductResponse.builder()
                    .imageName(imageProduct.getName())
                    .imageUrl("data:image/jpeg;base64," + base64Image) // Đảm bảo base64 có tiền tố đúng
                    .type(imageProduct.getType())
                    .build();
            listResponseImages.add(imageProductResponse);
        }
        return listResponseImages;
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