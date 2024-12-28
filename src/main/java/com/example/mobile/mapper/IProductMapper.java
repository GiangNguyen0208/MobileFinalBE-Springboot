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
    @Mapping(target = "rating", source = "rating")
    Product toProduct(ProductCreationReq req);
    @Mapping(target = "categoryName", source = "category.name")
    ProductResponse toProductResponse(Product product);
    void updateProduct(@MappingTarget Product product, ProductUpdateReq req);

}
