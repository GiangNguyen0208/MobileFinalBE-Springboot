package com.example.mobile.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "carts")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double totalPrice;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  // Khóa ngoại liên kết với Users
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)  // Khóa ngoại liên kết với Products
    private Product product;

}
