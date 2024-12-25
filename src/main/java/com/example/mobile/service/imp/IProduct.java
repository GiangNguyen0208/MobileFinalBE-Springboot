package com.example.mobile.service.imp;

import com.example.mobile.dto.request.ProductCreationReq;
import com.example.mobile.dto.request.ProductUpdateReq;
import com.example.mobile.dto.response.ProductResponse;
import com.example.mobile.dto.response.ProductWithShop;

import java.util.List;
import java.util.Map;

public interface IProduct {
    ProductResponse addProduct(ProductCreationReq req);

    List<ProductResponse> getListProduct();

    ProductResponse findProductById(int id);

    ProductResponse productUpdate(int id, ProductUpdateReq req);

    void deleteProduct(int id);

    List<ProductResponse> getListProductByCategory();

    ProductResponse findProductByName(String name);

    List<ProductWithShop> getListByShopName(String shopName);
}