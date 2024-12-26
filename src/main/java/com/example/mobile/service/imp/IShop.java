package com.example.mobile.service.imp;

import com.example.mobile.dto.request.ShopCreationReq;
import com.example.mobile.dto.request.ShopUpdateReq;
import com.example.mobile.dto.response.ShopResponse;

import java.util.List;

public interface IShop {
    ShopResponse createShop(ShopCreationReq req);
    List<ShopResponse> getListShop();
    ShopResponse findShopById(int id);
    ShopResponse shopUpdate(int id, ShopUpdateReq req);
    void deleteShop(int id);
    ShopResponse findShopByName(String name);
}
