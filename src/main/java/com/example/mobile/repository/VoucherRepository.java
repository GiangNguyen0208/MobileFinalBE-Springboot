package com.example.mobile.repository;


import com.example.mobile.dto.response.VoucherResponse;
import com.example.mobile.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
    boolean existsById(int voucherId);
    Optional<Voucher> findById(int voucherId);
    List<Voucher> getVouchersByShopId(int shopId);
    boolean existsByCode(String voucherCode);
}
