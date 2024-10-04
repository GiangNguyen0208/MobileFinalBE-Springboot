package com.example.mobile.entity;

import com.example.mobile.config.LicensesStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "licenses")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Licenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String cccdImageUrl;

    private Date issueDate, expiryDate;

    @Enumerated(EnumType.STRING)
    private LicensesStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private  User user;


}
