package com.example.mobile.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "categories")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String status;

    private boolean deleted;

    @OneToMany(mappedBy = "category")
    private Set<Product> productList = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;
}
