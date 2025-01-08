package com.example.mobile.mapper;

import com.example.mobile.dto.request.NotificationCreationReq;
import com.example.mobile.dto.request.NotificationUpdateReq;
import com.example.mobile.dto.response.NotificationResponse;

import com.example.mobile.entity.Notification;
import com.example.mobile.entity.Shop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface INotificationMapper {

    Notification toNotification(NotificationCreationReq req);
    @Mapping(target = "shopName", source = "shop.name")
    NotificationResponse toCategoryResponse(Notification notification);
    void updateNotification(@MappingTarget Notification notification, NotificationUpdateReq req);

}
