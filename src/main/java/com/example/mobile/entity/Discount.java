package com.example.mobile.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "discounts")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double priceMin;

    private double valueDiscount;

    @OneToMany(mappedBy = "discount")
    private Set<Order> orderSet = new HashSet<>();
}
