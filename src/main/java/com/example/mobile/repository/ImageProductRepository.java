package com.example.mobile.repository;

import com.example.mobile.entity.ImageProduct;
import com.example.mobile.entity.Location;
import com.example.mobile.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageProductRepository extends JpaRepository<ImageProduct, Integer> {
    Optional<ImageProduct> findByName(String productName);

    @Query("SELECT i FROM image_products i WHERE i.product.id = :productId ORDER BY i.id ASC")
    List<ImageProduct> findAllImagesByProductId(@Param("productId") int productId);


}
