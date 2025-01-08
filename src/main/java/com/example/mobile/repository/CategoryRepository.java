package com.example.mobile.repository;

import com.example.mobile.dto.response.CategoryResponse;
import com.example.mobile.entity.Category;
import com.example.mobile.entity.Product;
import com.example.mobile.entity.Shop;
import com.example.mobile.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByName(String categoryName);
    boolean existsByName(String name);
    List<Category> findByShopAndDeletedFalse(Shop shop);

    @Query("SELECT c FROM categories c WHERE c.deleted = false")
    List<Category> findAllActiveCategories();
}
