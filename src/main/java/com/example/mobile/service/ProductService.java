package com.example.mobile.service;

import com.example.mobile.entity.Product;
import com.example.mobile.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product getProductById(int id){
        Product product = productRepository.findProductById(id);
        return product;
    }
}
