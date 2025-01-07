package com.example.mobile.service.imp;

import com.example.mobile.dto.request.ProductCreationReq;
import com.example.mobile.dto.request.ProductUpdateReq;
import com.example.mobile.dto.response.ProductResponse;
import java.util.List;

public interface IProduct {
    ProductResponse addProduct(ProductCreationReq req);
    List<ProductResponse> getListProduct();
    ProductResponse findProductById(int id);
    ProductResponse productUpdate(int id, ProductUpdateReq req);
    void deleteProduct(int id);
    List<ProductResponse> getListProductByCategory(int id);
    ProductResponse findProductByName(String name);
}
