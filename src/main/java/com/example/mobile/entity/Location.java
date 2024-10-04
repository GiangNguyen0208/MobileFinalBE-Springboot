package com.example.mobile.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "locations")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String location_line1;

    private String location_line2;

    private String city;

    private String district;

    private Long latitude;

    private Long longtitude;

    private String country;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
