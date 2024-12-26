package com.example.mobile.service.imp;

import com.example.mobile.dto.request.ShopRequest;
import com.example.mobile.entity.Shop;

public interface IShop {
    public Shop addShop(ShopRequest shopRequest);
    public Shop updateShop(int id,ShopRequest shopRequest);
    public void deleteShop(int id);

}
