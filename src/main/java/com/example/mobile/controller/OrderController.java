package com.example.mobile.controller;

import com.example.mobile.dto.response.ApiResponse;
import com.example.mobile.dto.response.OrderResponse;
import com.example.mobile.service.imp.IOrder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {
    IOrder orderService;

    @GetMapping("/listOrder")
    public ApiResponse<List<OrderResponse>> getOrders() {
        // Lấy danh sách các đơn hàng đã được ánh xạ thành OrderResponse
        List<OrderResponse> orderResponses = orderService.getOrders();

        // Trả về ApiResponse với danh sách OrderResponse
        return ApiResponse.<List<OrderResponse>>builder()
                .result(orderResponses)
                .build();
    }
}
