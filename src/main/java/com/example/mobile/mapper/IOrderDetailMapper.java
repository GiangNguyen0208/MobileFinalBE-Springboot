package com.example.mobile.mapper;

import com.example.mobile.dto.response.OrderDetailResponse;
import com.example.mobile.dto.response.OrderResponse;
import com.example.mobile.entity.Cart;
import com.example.mobile.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IOrderDetailMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "totalPrice", source = "totalPrice")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "user", source = "user.id") // ánh xạ cart từ Order sang OrderResponse
    @Mapping(target = "product", source = "product.id") // ánh xạ paymentMethod từ Order sang OrderResponse
    OrderDetailResponse toOrderDetailResponse(Cart cart);
}
