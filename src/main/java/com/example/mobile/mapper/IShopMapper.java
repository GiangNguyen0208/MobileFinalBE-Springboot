package com.example.mobile.mapper;

import com.example.mobile.dto.request.ShopCreationReq;
import com.example.mobile.dto.request.ShopUpdateReq;
import com.example.mobile.dto.response.ShopResponse;

import com.example.mobile.entity.Shop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")

public interface IShopMapper {
    Shop toShop(ShopCreationReq req);
    @Mapping(target = "image", source = "image")
    @Mapping(target = "address", source = "address")
    ShopResponse toShopResponse(Shop shop);
    void updateShop(@MappingTarget Shop shop, ShopUpdateReq req);

}
