package com.example.mobile.entity;

import com.example.mobile.config.StatusShop;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "shops")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String address;

    @Enumerated(EnumType.STRING)
    private StatusShop status;

    private double rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "shop")
    private Set<Category> categoryList = new HashSet<>();
}
