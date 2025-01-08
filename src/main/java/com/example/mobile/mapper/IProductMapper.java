package com.example.mobile.mapper;


import com.example.mobile.dto.request.ProductCreationReq;
import com.example.mobile.dto.request.ProductUpdateReq;
import com.example.mobile.dto.response.ProductResponse;
import com.example.mobile.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IProductMapper {
    Product toProduct(ProductCreationReq req);
    void updateProduct(@MappingTarget Product product, ProductUpdateReq req);

}
