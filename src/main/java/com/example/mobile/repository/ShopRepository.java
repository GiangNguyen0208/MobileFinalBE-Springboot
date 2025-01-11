package com.example.mobile.repository;

import com.example.mobile.entity.Category;
import com.example.mobile.entity.Shop;
import com.example.mobile.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
    boolean existsByName(String name);
    Optional<Shop> findByName(String name);

    List<Shop> findDistinctByCategoryList_Name(String categoryName);
}
