package com.example.mobile.repository;

import com.example.mobile.entity.Category;
import com.example.mobile.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByName(String productName);
    Optional<Product> findByName(String productName);

    List<Product> findAllByCategory(Category category);


//    @Query(value = "SELECT * FROM products ORDER BY category_id ASC, id ASC", nativeQuery = true)
//    List<Product> findAllProductsByCategoryAndId();
    }


