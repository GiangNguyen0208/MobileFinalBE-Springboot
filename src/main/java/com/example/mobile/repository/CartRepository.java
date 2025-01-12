package com.example.mobile.repository;

import com.example.mobile.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByUser_IdAndProduct_Id(int userId, int productId);

    @Modifying
    void deleteAllByUserId(@Param("userId") int userId);

    List<Cart> findAllByUserId(@Param("userId") int userId);
}
