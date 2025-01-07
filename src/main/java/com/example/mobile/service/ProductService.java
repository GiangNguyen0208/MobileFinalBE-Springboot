package com.example.mobile.service;

import com.example.mobile.constant.RolePlay;
import com.example.mobile.dto.request.ProductCreationReq;
import com.example.mobile.dto.request.ProductUpdateReq;
import com.example.mobile.dto.request.UserCreationReq;
import com.example.mobile.dto.request.UserUpdateRequest;
import com.example.mobile.dto.response.CategoryResponse;
import com.example.mobile.dto.response.ImageProductResponse;
import com.example.mobile.dto.response.ProductResponse;
import com.example.mobile.dto.response.UserResponse;
import com.example.mobile.entity.*;
import com.example.mobile.exception.AddException;
import com.example.mobile.exception.ErrorCode;
import com.example.mobile.mapper.IProductMapper;
import com.example.mobile.mapper.IUserMapper;
import com.example.mobile.repository.CategoryRepository;
import com.example.mobile.repository.ProductRepository;
import com.example.mobile.repository.RoleRepository;
import com.example.mobile.repository.UserRepository;
import com.example.mobile.service.imp.IImageProduct;
import com.example.mobile.service.imp.IProduct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductService implements IProduct {
    ProductRepository productRepository;
    IProductMapper productMapper;
    CategoryRepository categoryRepository;
    IImageProduct imageProductService;

    @Override
    public ProductResponse addProduct(ProductCreationReq req) {

        if (productRepository.existsByName(req.getName())) {
            throw new AddException(ErrorCode.USER_EXISTED);
        }
        Product product = productMapper.toProduct(req);
        return productMapper.toProductResponse(productRepository.save(product));

    }

    @Override
    public List<ProductResponse> getListProduct() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        List<ProductResponse> productResponseList = new ArrayList<>();
        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        List<Product> productList = productRepository.findAll();
        for (Product p : productList) {
            ProductResponse productResponse = ProductResponse.builder()
                    .id(p.getId())
                    .name(p.getName())
                    .price(p.getPrice())
                    .des(p.getDescription())
                    .category(p.getCategory().getName())
                    .amount(p.getQuantity())
                    .rating(p.getRating())
                    .images(imageProductService.showProductImage(p.getId()))
                    .build();
            productResponseList.add(productResponse);
        }
        return  productResponseList;
    }

    @Override
    public ProductResponse findProductById(int id) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found!"));
        return ProductResponse.builder()
                .name(p.getName())
                .price(p.getPrice())
                .des(p.getDescription())
                .amount(p.getQuantity())
                .images(imageProductService.showProductImage(p.getId()))
                .build();
    }

    @Override
    public ProductResponse productUpdate(int id, ProductUpdateReq req) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found!"));

        productMapper.updateProduct(product, req);   // Use MappingTarget to mapping data update from req (new info) into old info

        return productMapper.toProductResponse(productRepository.save(product));
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponse> getListProductByCategory(int id) {
        List<ProductResponse> productResponseList = new ArrayList<>();
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found!"));;
        List<Product> productList = productRepository.findAllByCategory(category);
        for (Product product : productList) {
            ProductResponse productResponse = ProductResponse.builder()
                    .name(product.getName())
                    .price(product.getPrice())
                    .des(product.getDescription())
                    .amount(product.getQuantity())
                    .build();
            productResponseList.add(productResponse);
        }
        return productResponseList;
    }

    @Override
    public ProductResponse findProductByName(String name) {
        Product product = productRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Product not found!"));
        return productMapper.toProductResponse(product);
    }
}
