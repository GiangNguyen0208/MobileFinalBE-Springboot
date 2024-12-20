package com.example.mobile.service;

import com.example.mobile.constant.RolePlay;
import com.example.mobile.dto.request.ProductCreationReq;
import com.example.mobile.dto.request.ProductUpdateReq;
import com.example.mobile.dto.request.UserCreationReq;
import com.example.mobile.dto.request.UserUpdateRequest;
import com.example.mobile.dto.response.ProductResponse;
import com.example.mobile.dto.response.UserResponse;
import com.example.mobile.entity.Product;
import com.example.mobile.entity.Role;
import com.example.mobile.entity.User;
import com.example.mobile.exception.AddException;
import com.example.mobile.exception.ErrorCode;
import com.example.mobile.mapper.IProductMapper;
import com.example.mobile.mapper.IUserMapper;
import com.example.mobile.repository.ProductRepository;
import com.example.mobile.repository.RoleRepository;
import com.example.mobile.repository.UserRepository;
import com.example.mobile.service.imp.IProduct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductService implements IProduct {
    ProductRepository productRepository;
    IProductMapper productMapper;

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

        productMapper.updateProduct(product, req);   // Use MappingTarget to mapping data update from req (new info) into old info

        return productMapper.toProductResponse(productRepository.save(product));
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponse> getListProductByCategory() {
        return List.of();
    }

    @Override
    public ProductResponse findProductByName(int id) {
        return null;
    }

}
