package com.example.mobile.entity;

import com.example.mobile.constant.FoodStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private double price;

    private int quantity;

    private int position;

    private boolean deleted;

    private double rating;

    private String description;

    @Enumerated(EnumType.STRING)
    private FoodStatus status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // Mối quan hệ 1:N với Cart
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Cart> carts = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch =  FetchType.LAZY)
    private Set<ImageProduct> images = new HashSet<>();

}
