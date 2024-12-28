package com.example.mobile.service;

import com.example.mobile.dto.request.NotificationCreationReq;
import com.example.mobile.dto.request.NotificationUpdateReq;
import com.example.mobile.dto.response.NotificationResponse;
import com.example.mobile.entity.Notification;
import com.example.mobile.entity.Shop;
import com.example.mobile.mapper.ICategoryMapper;
import com.example.mobile.mapper.INotificationMapper;
import com.example.mobile.repository.CategoryRepository;
import com.example.mobile.repository.NotificationRepository;
import com.example.mobile.repository.ShopRepository;
import com.example.mobile.service.imp.INotification;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class NotificationService implements INotification {
    INotificationMapper notificationMapper;
    NotificationRepository notificationRepository;
     ShopRepository shopRepository;

    @Override
    public NotificationResponse addNotification(NotificationCreationReq req) {
        Notification notification = notificationMapper.toNotification(req);
        Shop shop = shopRepository.findById(req.getShopId())
                .orElseThrow(() -> new RuntimeException("Shop not found!"));
        notification.setShop(shop);
        return notificationMapper.toCategoryResponse(notificationRepository.save(notification));
    }

    @Override
    public List<NotificationResponse> getListNotification() {
        return notificationRepository.findAll().stream().map(notificationMapper::toCategoryResponse).toList();
    }

    @Override
    public NotificationResponse findNotificationById(int id) {
        return notificationMapper.toCategoryResponse(notificationRepository.findById(id).orElseThrow(()->new RuntimeException("Notification not found!")));
    }

    @Override
    public NotificationResponse notificationUpdate(int id, NotificationUpdateReq req) {
        Notification notification = notificationRepository.findById(id).orElseThrow(()->new RuntimeException("Notification not found!"));
        notificationMapper.updateNotification(notification,req);
        return notificationMapper.toCategoryResponse(notificationRepository.save(notification));
    }

    @Override
    public void deleteNotification(int id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public NotificationResponse findNotificationByTitle(String title) {
        Notification notification = notificationRepository.findByTitle(title).orElseThrow(()->new RuntimeException("Notification not found!"));
        return notificationMapper.toCategoryResponse(notification);
    }

    @Override
    public List<NotificationResponse> getListNotificationByShopId(int shopId) {
        return notificationRepository.getListNotificationByShopId(shopId).stream().map(notificationMapper::toCategoryResponse).toList();
    }

}
