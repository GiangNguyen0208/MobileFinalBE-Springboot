package com.example.mobile.repository;


import com.example.mobile.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
    boolean existsById(int voucherId);
    Optional<Voucher> findById(int voucherId);
}
