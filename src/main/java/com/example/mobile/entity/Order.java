package com.example.mobile.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voucherId")
    private Voucher voucher;

    @ColumnDefault("0")
    @Column(name = "discount")
    private Double discount;

    @ColumnDefault("0")
    @Column(name = "totalProduct")
    private Integer totalProduct;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Double amount;

    @ColumnDefault("'PENDING'")
    @Column(name = "status", columnDefinition = "VARCHAR(255) DEFAULT 'PENDING'")
    private String status;

    @Column(name = "createAt")
    private Instant createAt;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> orderdetails = new LinkedHashSet<>();

}