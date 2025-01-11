package com.example.mobile.service.imp;

import com.example.mobile.dto.request.CategoryCreationReq;
import com.example.mobile.dto.request.CategoryCreationReq;
import com.example.mobile.dto.request.CategoryUpdateReq;
import com.example.mobile.dto.response.CategoryResponse;
import com.example.mobile.entity.Category;

import java.util.List;

public interface ICategory {
    List<CategoryResponse> getListCategory(int shopID);
    List<CategoryResponse> getListCategory();
    CategoryResponse addCategory(CategoryCreationReq req);
    CategoryResponse findCategoryById(int id);
    CategoryResponse categoryUpdate(int id, CategoryUpdateReq req);
    void deleteCategory(int id);
    CategoryResponse findCategoryByName(String name);
}
