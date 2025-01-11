package com.example.mobile.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "image_products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ImageProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String name;
    String type;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    byte[] linkImage;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


}


