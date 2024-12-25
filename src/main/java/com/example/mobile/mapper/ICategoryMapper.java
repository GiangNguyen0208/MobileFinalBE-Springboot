package com.example.mobile.mapper;

import com.example.mobile.dto.request.CategoryCreationReq;
import com.example.mobile.dto.request.CategoryUpdateReq;
import com.example.mobile.dto.response.CategoryResponse;
import com.example.mobile.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ICategoryMapper {
    Category toCategory(CategoryCreationReq req);
    @Mapping(target = "shopId", source = "shop.id")
    CategoryResponse toCategoryResponse(Category category);

    void updateCategory(@MappingTarget Category category, CategoryUpdateReq req);

}
