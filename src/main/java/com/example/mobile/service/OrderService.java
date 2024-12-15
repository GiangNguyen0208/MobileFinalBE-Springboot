package com.example.mobile.service;

import com.example.mobile.dto.response.OrderResponse;
import com.example.mobile.mapper.IOrderMapper;
import com.example.mobile.repository.OrderRepository;
import com.example.mobile.service.imp.IOrder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderService implements IOrder {
    OrderRepository orderRepository;
    IOrderMapper orderMapper;

    @Override
    public List<OrderResponse> getOrders() {
        return orderRepository.findAll().stream()
              git  .map(orderMapper::toOrderResponse) // Ánh xạ từ Order sang OrderResponse
                .collect(Collectors.toList());
    }
}

