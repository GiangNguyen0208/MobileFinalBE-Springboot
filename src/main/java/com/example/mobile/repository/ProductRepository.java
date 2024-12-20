package com.example.mobile.repository;

import com.example.mobile.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByName(String productName);
    Optional<Product> findByName(String productName);
}
