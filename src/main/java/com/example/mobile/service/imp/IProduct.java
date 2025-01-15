package com.example.mobile.service.imp;

import com.example.mobile.dto.request.ProductCreationReq;
import com.example.mobile.dto.request.ProductUpdateReq;
import com.example.mobile.dto.response.CategoryResponse;
import com.example.mobile.dto.response.ProductResponse;
import com.example.mobile.dto.response.ProductWithShop;
import com.example.mobile.entity.Product;

import java.util.List;
import java.util.Map;

public interface IProduct {
    boolean addProduct(ProductCreationReq req);

    List<ProductResponse> getListProduct();

    ProductResponse findProductById(int id);

    ProductResponse productUpdate(int id, ProductUpdateReq req);

    void deleteProduct(int id);
    List<ProductResponse> getListProductByCategory(int id);

    List<ProductResponse> getListProductByShopID(int shopId);


    ProductResponse findProductByName(String name);

    List<ProductWithShop> getListByShopName(String shopName);

    List<ProductResponse> findAllByNameContains(String productName);
}