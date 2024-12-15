package com.example.mobile.repository;

import com.example.mobile.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<Cart, Integer> {
}
