package com.example.mobile.repository;

import com.example.mobile.entity.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PermissionsRepository extends JpaRepository<Permissions, String> {
}
