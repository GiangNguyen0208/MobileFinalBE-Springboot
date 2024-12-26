package com.example.mobile.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity(name = "invalidatedTokens")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InvalidatedToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    Date expiryTime;
}
