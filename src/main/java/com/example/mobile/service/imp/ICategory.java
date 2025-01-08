package com.example.mobile.service.imp;

import com.example.mobile.dto.request.CategoryCreationReq;
import com.example.mobile.dto.request.CategoryCreationReq;
import com.example.mobile.dto.request.CategoryUpdateReq;
import com.example.mobile.dto.response.CategoryResponse;
import com.example.mobile.entity.Category;

import java.util.List;

public interface ICategory {
    CategoryResponse addCategory(CategoryCreationReq req);
    List<CategoryResponse> getListCategory();
    CategoryResponse findCategoryById(int id);
    CategoryResponse categoryUpdate(int id, CategoryUpdateReq req);
    void deleteCategory(int id);
    CategoryResponse findCategoryByName(String name);
}
