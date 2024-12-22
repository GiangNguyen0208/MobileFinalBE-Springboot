package com.example.mobile.dto.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationCreationReq {
    int shopId;
    String title; // Tiêu đề thông báo
    String message; // Nội dung thông báo
}
