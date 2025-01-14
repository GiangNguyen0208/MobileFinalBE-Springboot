package com.example.mobile.repository;

import com.example.mobile.entity.Orderdetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<Orderdetail, Integer> {
    @Query("SELECT od FROM Orderdetail od WHERE od.order.id = :orderId")
    List<Orderdetail> findAllByOrderId(Integer orderId);


}
