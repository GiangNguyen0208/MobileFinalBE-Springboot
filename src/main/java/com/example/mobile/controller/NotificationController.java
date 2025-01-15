package com.example.mobile.controller;

import com.example.mobile.dto.request.NotificationCreationReq;
import com.example.mobile.dto.request.NotificationUpdateReq;
import com.example.mobile.dto.response.ApiResponse;
import com.example.mobile.dto.response.NotificationResponse;
import com.example.mobile.service.imp.INotification;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationController {
    INotification notificationService;

    @PostMapping("/add")
    ApiResponse<NotificationResponse> addNotification(@RequestBody @Valid NotificationCreationReq notificationCreationReq) {
        ApiResponse<NotificationResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(notificationService.addNotification(notificationCreationReq));
        return apiResponse;
    }
    @GetMapping("/listNotification")
    ApiResponse<List<NotificationResponse>> getListNotification() {
        return ApiResponse.<List<NotificationResponse>>builder()
                .result(notificationService.getListNotification())
                .build();
    }

    @GetMapping("/findId/{notificationId}")
    ApiResponse<NotificationResponse>  findById(@PathVariable("notificationId") int notificationId) {
        ApiResponse<NotificationResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(notificationService.findNotificationById(notificationId));
        return apiResponse;
    }

    @GetMapping("/findTitle/{notificationTitle}")
    ApiResponse<NotificationResponse>  findByTitle(@PathVariable("notificationTitle") String notificationTitle) {
        ApiResponse<NotificationResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(notificationService.findNotificationByTitle(notificationTitle));
        return apiResponse;
    }

    @GetMapping("/listNotification/{shopId}")
    ApiResponse<List<NotificationResponse>> listNotificationByShopId(@PathVariable("shopId") int shopId) {
        return ApiResponse.<List<NotificationResponse>>builder()
                .result(notificationService.getListNotificationByShopId(shopId))
                .build();
    }

    @PutMapping("/update/{notificationId}")
    ApiResponse<NotificationResponse> updateNotification(@PathVariable("notificationId") int notificationId, @RequestBody NotificationUpdateReq notificationUpdateReq) {
        return ApiResponse.<NotificationResponse>builder()
                .result(notificationService.notificationUpdate(notificationId,notificationUpdateReq)).build();
    }

    @DeleteMapping("/delete/{notificationId}")
    public ResponseEntity<String> deleteNotification(@PathVariable("notificationId") int notificationId) {
        try {
            // Gọi service để xóa thông báo
            notificationService.deleteNotification(notificationId);
            // Trả về phản hồi thành công
            return ResponseEntity.ok("Notification has been deleted!");
        } catch (Exception e) {
            // Ghi log lỗi nếu cần thiết
            e.printStackTrace();
            // Trả về phản hồi lỗi
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while deleting the notification.");
        }
    }
}
