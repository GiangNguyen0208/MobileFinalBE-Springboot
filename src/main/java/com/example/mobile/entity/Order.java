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
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;

    @ColumnDefault("0")
    @Column(name = "discount")
    private Double discount;

    @ColumnDefault("0")
    @Column(name = "total_product")
    private Integer totalProduct;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Double amount;

    @ColumnDefault("PENDING")
    @Lob
    @Column(name = "status")
    private String status;

    @Column(name = "create_at")
    private Instant createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Orderdetail> orderdetails = new LinkedHashSet<>();

}