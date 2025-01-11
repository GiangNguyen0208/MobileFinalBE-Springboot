package com.example.mobile.service;

import com.example.mobile.constant.RolePlay;
import com.example.mobile.dto.request.ProductCreationReq;
import com.example.mobile.dto.request.ProductUpdateReq;
import com.example.mobile.dto.request.UserCreationReq;
import com.example.mobile.dto.request.UserUpdateRequest;
import com.example.mobile.dto.response.ImageProductResponse;
import com.example.mobile.dto.response.ProductResponse;
import com.example.mobile.dto.response.ProductWithShop;
import com.example.mobile.dto.response.UserResponse;
import com.example.mobile.entity.*;
import com.example.mobile.exception.AddException;
import com.example.mobile.exception.ErrorCode;
import com.example.mobile.mapper.IProductMapper;
import com.example.mobile.mapper.IUserMapper;
import com.example.mobile.repository.CategoryRepository;
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
            throw new AddException(ErrorCode.PRODUCT_EXISTED);
        }
        Category category = categoryRepository.findById(req.getCategoryId()).orElseThrow(()-> new RuntimeException("Category not found"));
        Product product = productMapper.toProduct(req);
        product.setCategory(category);
        return productMapper.toProductResponse(productRepository.save(product));

    }

    @Override
    public List<ProductResponse> getListProduct() {
        List<ProductResponse> productResponseList = new ArrayList<>();
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

//        return productRepository.findAll().stream()
//                .map(productMapper::toProductResponse).toList();
        List<Product> productList = productRepository.findAll();
        for (Product p : productList) {
            ProductResponse productResponse = ProductResponse.builder()
                    .id(p.getId())
                    .rating(p.getRating())
                    .name(p.getName())
                    .price(p.getPrice())
                    .des(p.getDescription())
                    .amount(p.getQuantity())
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
        Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found!"));
        productMapper.updateProduct(product,req);
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

    @Override
    public List<ProductWithShop> getListByShopName(String shopName) {
        // Lấy kết quả từ repository
        List<Object[]> results = productRepository.findProductsByShopName(shopName);
        List<ProductWithShop> productWithShopList = new ArrayList<>();

        for (Object[] result : results) {
            ProductWithShop productWithShop = getProductWithShop(result);
            productWithShopList.add(productWithShop);
        }
        return productWithShopList;
    }

    private static ProductWithShop getProductWithShop(Object[] result) {
        Integer productId = (Integer) result[0];
        Double productRating = (Double) result[1]; // product price
        String productName = (String) result[2];
        Double productPrice = (Double) result[3]; // product price
        Integer productQuantity = (Integer) result[4];
        String productDescription =(String) result[5];
        String shopNameFromResult = (String) result[6];
        String categoryNameFromResult = (String) result[7];// shop name

        ProductWithShop productWithShop = new ProductWithShop();
        productWithShop.setId(productId);
        productWithShop.setRating(productRating);
        productWithShop.setName(productName);
        productWithShop.setPrice(productPrice);
        productWithShop.setQuantity(productQuantity);
        productWithShop.setDescription(productDescription);
        productWithShop.setShopName(shopNameFromResult);
        productWithShop.setCategoryName(categoryNameFromResult);
        return productWithShop;
    }


}
