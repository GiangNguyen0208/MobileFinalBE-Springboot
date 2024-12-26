package com.example.mobile.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "permissions")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Permissions {

    @Id
    String name;

    String description;
}
