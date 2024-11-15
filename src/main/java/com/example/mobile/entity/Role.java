package com.example.mobile.entity;

import com.example.mobile.constant.RolePlay;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "roles")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private RolePlay roleName;

    @OneToMany(mappedBy = "role")
    private Set<User> userList = new HashSet<>();
}
