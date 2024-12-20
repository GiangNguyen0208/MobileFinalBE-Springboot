package com.example.mobile.mapper;

import com.example.mobile.dto.request.ShopCreationReq;
import com.example.mobile.dto.request.ShopUpdateReq;
import com.example.mobile.dto.response.ShopResponse;
import com.example.mobile.entity.Shop;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")

public interface IProductMapper {
    Shop toShop(ShopCreationReq req);
    ShopResponse toShopResponse(Shop shop);
    void updateShop(@MappingTarget Shop shop, ShopUpdateReq req);
}
