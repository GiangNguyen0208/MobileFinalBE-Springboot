package com.example.mobile.mapper;


import com.example.mobile.dto.request.ProductCreationReq;
import com.example.mobile.dto.request.ProductUpdateReq;
import com.example.mobile.dto.response.ProductResponse;
import com.example.mobile.dto.response.ProductWithShop;
import com.example.mobile.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IProductMapper {
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "rating", source = "rating")
    Product toProduct(ProductCreationReq req);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "rating", source = "rating")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "des", source = "description")
    @Mapping(target = "quantity", source = "quantity")
    ProductResponse toProductResponse(Product product);
    void updateProduct(@MappingTarget Product product, ProductUpdateReq req);

}
