package com.example.mobile.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "notifications")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  // Nếu bạn tin rằng ID sẽ không vượt quá giới hạn của int trong Java

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    private Shop shop;

    @Column(name = "title", nullable = false, length = 255)
    private String title; // Tiêu đề thông báo

    @Column(name = "message", nullable = false, columnDefinition = "TEXT")
    private String message; // Nội dung thông báo

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

}
