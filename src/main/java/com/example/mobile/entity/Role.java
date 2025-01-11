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
    private String name;

    private String description;

    @ManyToMany
    private Set<Permissions> permissions;

}
