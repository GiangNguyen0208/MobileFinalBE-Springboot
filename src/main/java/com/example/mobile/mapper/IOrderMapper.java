package com.example.mobile.mapper;


import com.example.mobile.dto.request.OrderDetailReq;
import com.example.mobile.dto.request.OrderReq;
import com.example.mobile.dto.response.OrderDetailResponse;
import com.example.mobile.dto.response.OrderResponse;
import com.example.mobile.entity.Order;
import com.example.mobile.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IOrderMapper {
    @Mapping(source = "voucherId", target = "voucher.id")
    @Mapping(source = "orderDetails", target = "orderdetails")
    Order toEntity(OrderReq orderRequest);

    @Mapping(source = "voucher.id", target = "voucherId")
    @Mapping(source = "orderdetails", target = "orderDetails")
    OrderResponse toResponse(Order order);

    @Mapping(source = "productId", target = "product.id")
    OrderDetail toEntity(OrderDetailReq orderDetailRequest);

    OrderDetailResponse toResponse(OrderDetail orderdetail);

    List<OrderDetailResponse> toResponseList(List<OrderDetail> orderdetails);

}
