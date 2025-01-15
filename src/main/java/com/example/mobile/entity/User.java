package com.example.mobile.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    private String phone;

    private String firstname;

    private String lastname;

    private String email;

    private boolean status;

    private String avatar;

    private int limit_discount;

    private boolean deleted;

    private Date createAt;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private Set<Comment> comments = new HashSet<>();

    @ManyToMany
    private Set<Role> roles;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private Set<Shop> shopList = new HashSet<>();

    private  String address ;

    // Mối quan hệ 1:N với Cart
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Cart> carts = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Licenses> licensesSet = new HashSet<>();
}
