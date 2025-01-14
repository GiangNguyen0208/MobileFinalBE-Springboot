package com.example.mobile.controller;

import com.example.mobile.dto.request.OrderReq;
import com.example.mobile.dto.response.ApiResponse;
import com.example.mobile.dto.response.OrderResponse;
import com.example.mobile.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/create")
    public ApiResponse<OrderResponse> createOrder(
            @RequestBody OrderReq orderReq) {

        ApiResponse<OrderResponse> response = new ApiResponse<>();

        try {
            // Gọi service để tạo đơn hàng
            OrderResponse orderResponse = orderService.createOrder(orderReq);

            // Nếu thành công, cập nhật code và message
            response.setCode(1000);
            response.setMesg("Order created successfully");
            response.setResult(orderResponse);
        } catch (IllegalArgumentException e) {
            // Nếu có lỗi, cập nhật code và message lỗi
            response.setCode(400);
            response.setMesg(e.getMessage());
        }
        catch (Exception e) {
            response.setCode(500);
            response.setMesg(e.getMessage());
        }

        return response;
    }


    @GetMapping("/user")
    public ApiResponse<List<OrderResponse>> getAllOrders() {
        ApiResponse<List<OrderResponse>> response = new ApiResponse<>();
        try {
            List<OrderResponse> orders = orderService.getAllOrders();
            response.setCode(1000);
            response.setMesg("Orders retrieved successfully");
            response.setResult(orders);
        } catch (Exception e) {
            response.setCode(500);
            response.setMesg("Internal server error");
        }
        return response;
    }

    // API lấy đơn hàng theo ID
    @GetMapping("/{orderId}")
    public ApiResponse<OrderResponse> getOrderById(@PathVariable Integer orderId) {
        ApiResponse<OrderResponse> response = new ApiResponse<>();
        try {
            OrderResponse orderResponse = orderService.getOrderById(orderId);
            response.setCode(1000);
            response.setMesg("Order retrieved successfully");
            response.setResult(orderResponse);
        } catch (Exception e) {
            response.setCode(500);
            response.setMesg("Order not found or internal server error");
        }
        return response;
    }
}
