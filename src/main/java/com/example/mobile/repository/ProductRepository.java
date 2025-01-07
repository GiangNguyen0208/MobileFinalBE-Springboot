package com.example.mobile.repository;

import com.example.mobile.entity.Category;
import com.example.mobile.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByName(String productName);
    Optional<Product> findByName(String productName);

    List<Product> findAllByCategory(Category category);
    List<Product> findProductByCategory(Category category);
    }


