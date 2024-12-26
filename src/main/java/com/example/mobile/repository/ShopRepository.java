package com.example.mobile.repository;

import com.example.mobile.entity.Shop;
import com.example.mobile.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop,Integer> {


    boolean existsByName(String name);

    Optional<Object> findByName(String name);
}
