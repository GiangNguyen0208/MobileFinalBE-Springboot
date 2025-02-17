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

    List<Product> findAllByCategoryAndDeletedFalse(Category category);

    @Query(value = "SELECT * FROM products ORDER BY category_id ASC, id ASC", nativeQuery = true)
    List<Product> findAllProductsByCategoryAndId();

    @Query("SELECT p FROM products p JOIN p.category c WHERE c.shop.id = :shopId")
    List<Product> findByShopId(@Param("shopId") int shopId);

    @Query("SELECT p.name, p.price, p.quantity, s.name AS shopName FROM products p " +
            "JOIN p.category c " +
            "JOIN c.shop s " +
            "WHERE s.name = :shopName")
    List<Object[]> findProductsByShopName(@Param("shopName") String shopName);

    List<Product> findAllByNameContains(String productName);

}


