package com.example.mobile.service.imp;

import com.example.mobile.dto.response.OrderDetailResponse;
import com.example.mobile.dto.response.OrderResponse;

import java.util.List;

public interface IOrderDetail {
    List<OrderDetailResponse> getOrderDetail();
}
