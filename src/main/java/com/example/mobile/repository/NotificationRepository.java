package com.example.mobile.repository;

import com.example.mobile.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    Optional<Notification> findByTitle(String notificationTitle);

    @Query(value = "SELECT * FROM notifications WHERE shop_id = :shopId", nativeQuery = true)
    List<Notification> getListNotificationByShopId(@Param("shopId") int shopId);

}
