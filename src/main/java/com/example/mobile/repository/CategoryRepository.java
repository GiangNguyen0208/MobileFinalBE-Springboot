package com.example.mobile.repository;

import com.example.mobile.entity.Category;
import com.example.mobile.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
