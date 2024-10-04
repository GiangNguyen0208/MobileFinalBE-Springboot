package com.example.mobile.entity;

import com.example.mobile.config.MethodType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "payment_methods")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private MethodType methodType;

    private Date createAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "paymentMethod")
    private Set<Order> orderSet = new HashSet<>();

    @OneToMany(mappedBy = "paymentMethod")
    private Set<Transaction> transactionSet = new HashSet<>();
}

