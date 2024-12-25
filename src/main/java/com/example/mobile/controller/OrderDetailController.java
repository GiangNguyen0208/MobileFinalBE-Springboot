package com.example.mobile.controller;

import com.example.mobile.dto.response.ApiResponse;
import com.example.mobile.dto.response.OrderDetailResponse;
import com.example.mobile.dto.response.OrderResponse;

import com.example.mobile.service.imp.IOrderDetail;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderDetailController {
   IOrderDetail orderDetailService;

    @GetMapping("/orderDetail")
    public ApiResponse<List<OrderDetailResponse>> getOrderDetail() {
        // Lấy danh sách các đơn hàng đã được ánh xạ thành OrderResponse
        List<OrderDetailResponse> orderDetailResponses = orderDetailService.getOrderDetail();

        // Trả về ApiResponse với danh sách OrderResponse
        return ApiResponse.<List<OrderDetailResponse>>builder()
                .result(orderDetailResponses)
                .build();
    }
}
