package com.example.mobile.mapper;

import com.example.mobile.dto.response.OrderResponse;
import com.example.mobile.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IOrderMapper {
    @Mapping(target = "cart", source = "cart.id") // ánh xạ cart từ Order sang OrderResponse
    @Mapping(target = "discount", source = "discount.id") // ánh xạ discount từ Order sang OrderResponse
    @Mapping(target = "paymentMethod", source = "paymentMethod.id") // ánh xạ paymentMethod từ Order sang OrderResponse
    @Mapping(target = "createAt", source = "createAt") // ánh xạ createAt từ Order sang OrderResponse
    OrderResponse toOrderResponse(Order order);
}
