package com.example.mobile.repository;

import com.example.mobile.entity.Product;
import com.example.mobile.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
