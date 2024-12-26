package com.example.mobile.repository;

import com.example.mobile.constant.RolePlay;
import com.example.mobile.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
