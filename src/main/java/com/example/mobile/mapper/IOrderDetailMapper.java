package com.example.mobile.mapper;


import com.example.mobile.dto.request.OrderDetailReq;
import com.example.mobile.dto.response.OrderDetailResponse;

import com.example.mobile.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IOrderDetailMapper {

    // Chuyển OrderDetailRequest sang Orderdetail Entity
    @Mapping(source = "productId", target = "product.id")  // giả sử bạn có entity Product với id
    OrderDetail toEntity(OrderDetailReq orderDetailRequest);

    // Chuyển Orderdetail Entity sang OrderDetailResponse
    @Mapping(source = "product.id", target = "productId")
    OrderDetailResponse toResponse(OrderDetail orderdetail);
}
