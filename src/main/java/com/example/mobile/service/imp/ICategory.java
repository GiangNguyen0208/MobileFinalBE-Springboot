package com.example.mobile.service.imp;

import com.example.mobile.dto.response.CategoryResponse;
import com.example.mobile.dto.response.ProductResponse;
import com.example.mobile.entity.Category;

import java.util.List;

public interface ICategory {
    List<CategoryResponse> getListCategory(int shopID);
    List<CategoryResponse> getListCategory();
}
