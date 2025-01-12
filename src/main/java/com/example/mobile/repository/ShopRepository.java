package com.example.mobile.repository;

import com.example.mobile.entity.Shop;
import com.example.mobile.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer> {
    boolean existsByName(String name);
    Optional<Shop> findByName(String name);
    List<Shop> findByStatus(String status);
    @Query("SELECT s.id FROM shops s WHERE s.status = 'OPEN'")
    List<Integer> findOpenShopIds();

    @Query("SELECT s FROM shops s WHERE s.status = 'OPEN' AND s.user.id = :userId")
    List<Shop> findOpenShopsByUserId(@Param("userId") int userId);
}
