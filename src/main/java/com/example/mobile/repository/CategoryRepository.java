package com.example.mobile.repository;

import com.example.mobile.entity.Category;
import com.example.mobile.entity.Product;
import com.example.mobile.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByName(String categoryName);
    boolean existsByName(String name);
    List<Category> findByShopAndDeletedFalse(Shop shop);
}
