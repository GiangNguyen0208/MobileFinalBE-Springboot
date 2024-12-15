package com.example.mobile.service;

import com.example.mobile.dto.response.OrderDetailResponse;
import com.example.mobile.mapper.IOrderDetailMapper;
import com.example.mobile.repository.OrderDetailRepository;
import com.example.mobile.service.imp.IOrderDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service  // Đảm bảo OrderDetailService là một Spring Bean
@RequiredArgsConstructor
public class OrderDetailService implements IOrderDetail {
    private final OrderDetailRepository orderDetailRepository;
    private final IOrderDetailMapper orderDetailMapper;

    @Override
    public List<OrderDetailResponse> getOrderDetail() {
        // Lấy tất cả các chi tiết đơn hàng và ánh xạ thành OrderDetailResponse
        return orderDetailRepository.findAll().stream()
                .map(orderDetailMapper::toOrderDetailResponse)
                .collect(Collectors.toList());
    }
}
