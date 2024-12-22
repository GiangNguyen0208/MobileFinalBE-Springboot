package com.example.mobile.service.imp;

import com.example.mobile.dto.request.NotificationCreationReq;
import com.example.mobile.dto.request.NotificationUpdateReq;
import com.example.mobile.dto.response.NotificationResponse;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface INotification {
    NotificationResponse addNotification(NotificationCreationReq req);
    List<NotificationResponse> getListNotification();
    NotificationResponse findNotificationById(int id);
    NotificationResponse notificationUpdate(int id, NotificationUpdateReq req);
    void deleteNotification(int id);
    NotificationResponse findNotificationByTitle(String title);
    List<NotificationResponse> getListNotificationByShopId(int shopId);
}
