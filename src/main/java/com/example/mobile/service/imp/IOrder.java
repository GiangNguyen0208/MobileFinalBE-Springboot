package com.example.mobile.service.imp;

import com.example.mobile.dto.response.OrderResponse;

import java.util.List;

public interface IOrder {
    List<OrderResponse> getOrders();
}
