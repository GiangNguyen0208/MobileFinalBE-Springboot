package com.example.mobile.mapper;

import com.example.mobile.dto.request.ProductCreationReq;
import com.example.mobile.dto.request.ProductUpdateReq;
import com.example.mobile.dto.response.ProductResponse;
import com.example.mobile.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IProductMapper {
    @Mapping(target = "quantity", source = "quantity")
    Product toProduct(ProductCreationReq req);
    ProductResponse toProductResponse(Product product);
    void updateProduct(@MappingTarget Product product, ProductUpdateReq req);

}
