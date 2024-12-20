package com.example.mobile.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "vouchers")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double priceMin;

    private double valueDiscount;

    @OneToMany(mappedBy = "voucher")
    private Set<Order> orderSet = new HashSet<>();
}
