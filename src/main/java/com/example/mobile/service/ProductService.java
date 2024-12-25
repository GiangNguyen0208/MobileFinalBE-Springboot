package com.example.mobile.service;

import com.example.mobile.dto.request.ProductCreationReq;
import com.example.mobile.dto.request.ProductUpdateReq;
import com.example.mobile.dto.response.ProductResponse;
import com.example.mobile.dto.response.ProductWithShop;
import com.example.mobile.entity.Category;
import com.example.mobile.entity.Notification;
import com.example.mobile.entity.Product;
import com.example.mobile.entity.Shop;
import com.example.mobile.exception.AddException;
import com.example.mobile.exception.ErrorCode;
import com.example.mobile.mapper.IProductMapper;
import com.example.mobile.repository.CategoryRepository;
import com.example.mobile.repository.ProductRepository;
import com.example.mobile.service.imp.IProduct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductService implements IProduct {
    ProductRepository productRepository;
    IProductMapper productMapper;
    CategoryRepository categoryRepository;

    @Override
    public ProductResponse addProduct(ProductCreationReq req) {
        if (productRepository.existsByName(req.getName())) {
            throw new AddException(ErrorCode.PRODUCT_EXISTED);
        }
        Product product = new Product();
        Category category = categoryRepository.findById(req.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found!"));
        product.setName(req.getName());
        product.setPrice(req.getPrice());
        product.setQuantity(req.getQuantity());
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        System.out.println("Saved product: " + savedProduct);
        return productMapper.toProductResponse(savedProduct);

    }

    @Override
    public List<ProductResponse> getListProduct() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return productRepository.findAll().stream()
                .map(productMapper::toProductResponse).toList();
    }

    @Override
    public ProductResponse findProductById(int id) {
        return productMapper.toProductResponse(productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found!")));
    }

    @Override
    public ProductResponse productUpdate(int id, ProductUpdateReq req) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found!"));
        Category category = categoryRepository.findById(req.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Shop not found!"));
        product.setName(req.getName());
        product.setPrice(req.getPrice());
        product.setQuantity(req.getQuantity());
        product.setCategory(category);
        return productMapper.toProductResponse(productRepository.save(product));
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponse> getListProductByCategory() {
        return productRepository.findAllProductsByCategoryAndId().stream()
                .map(productMapper::toProductResponse).toList();
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
