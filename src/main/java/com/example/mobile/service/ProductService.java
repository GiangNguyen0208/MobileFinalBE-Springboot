package com.example.mobile.service;

import com.example.mobile.dto.request.ProductCreationReq;
import com.example.mobile.dto.request.ProductUpdateReq;
import com.example.mobile.dto.response.ImageProductResponse;
import com.example.mobile.dto.response.ProductResponse;
import com.example.mobile.dto.response.ProductWithShop;
import com.example.mobile.entity.Category;
import com.example.mobile.entity.Product;
import com.example.mobile.exception.AddException;
import com.example.mobile.exception.ErrorCode;
import com.example.mobile.mapper.IProductMapper;
import com.example.mobile.repository.CategoryRepository;
import com.example.mobile.repository.ImageProductRepository;
import com.example.mobile.repository.ProductRepository;
import com.example.mobile.service.imp.IImageProduct;
import com.example.mobile.service.imp.IProduct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductService implements IProduct {
    private final ImageProductRepository imageProductRepository;
    ProductRepository productRepository;
    IProductMapper productMapper;
    IImageProduct imageProductService;
    CategoryRepository categoryRepository;

    @Override
    public ProductResponse addProduct(ProductCreationReq req) {
        if (productRepository.existsByName(req.getName())) {
            throw new AddException(ErrorCode.PRODUCT_EXISTED);
        }
        ProductResponse productResponse = ProductResponse.builder()
                .name(req.getName())
                .quantity(req.getQuantity())
                .price(req.getPrice())
                .rating(req.getRating())
                .des(req.getDescription())
                .categoryId(req.getCategoryId())
                .build();
        return productResponse;
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
                    .categoryId(p.getCategory().getId())
                    .name(p.getName())
                    .price(p.getPrice())
                    .des(p.getDescription())
                    .categoryName(p.getCategory().getName())
                    .quantity(p.getQuantity())
                    .rating(p.getRating())
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
                .quantity(p.getQuantity())
                .build();
    }

    @Override
    public ProductResponse productUpdate(int id, ProductUpdateReq req) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found!"));

        ProductResponse productResponse = ProductResponse.builder()
                .categoryId(req.getCategoryId())
                .des(req.getDescription())
                .name(req.getName())
                .price(req.getPrice())
                .quantity(req.getQuantity())
                .build();
        return productResponse;
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponse> getListProductByCategory(int id) {
        List<ProductResponse> productResponseList = new ArrayList<>();
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found!"));
        List<Product> productList = productRepository.findAllByCategory(category);
        for (Product product : productList) {
            List<ImageProductResponse> imageProducts = imageProductService.showProductImage(product.getId());
            ProductResponse productResponse = ProductResponse.builder()
                    .name(product.getName())
                    .price(product.getPrice())
                    .des(product.getDescription())
                    .quantity(product.getQuantity())
                    .build();
            productResponseList.add(productResponse);
        }
        return productResponseList;
    }

    @Override
    public List<ProductResponse> getListProductByCategory() {
        return null;
    }

    @Override
    public ProductResponse findProductByName(String name) {
        Product product = productRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Product not found!"));
        ProductResponse productResponse = ProductResponse.builder()
                .name(product.getName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .rating(product.getRating())
                .des(product.getDescription())
                .categoryId(product.getCategory().getId())
                .build();
        return productResponse;
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
        String productName = (String) result[0];
        Double productPrice = (Double) result[1]; // product price
        Integer productQuantity = (Integer) result[2]; // product quantity
        String shopNameFromResult = (String) result[3]; // shop name

        ProductWithShop productWithShop = new ProductWithShop();
        productWithShop.setName(productName);
        productWithShop.setPrice(productPrice);
        productWithShop.setQuantity(productQuantity);
        productWithShop.setShopName(shopNameFromResult);
        return productWithShop;
    }


}
