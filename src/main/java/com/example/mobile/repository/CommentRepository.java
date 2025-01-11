package com.example.mobile.repository;

import com.example.mobile.entity.Comment;
import org.hibernate.sql.results.graph.FetchList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

    @Query(value = "SELECT * FROM comments WHERE shop_id = :shopId", nativeQuery = true)
    List<Comment> getListByShopId(@Param("shopId") int shopId);

    @Query(value = "SELECT * FROM comments WHERE user_id = :userId", nativeQuery = true)
    List<Comment> getListByUserId(@Param("userId")int userId);

    @Query(value = "SELECT * FROM comments WHERE product_id = :productId", nativeQuery = true)
    List<Comment> getListByProductId(@Param("productId")int productId);
}
