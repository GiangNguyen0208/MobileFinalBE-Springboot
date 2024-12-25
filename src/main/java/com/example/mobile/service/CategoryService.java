package com.example.mobile.service;

import com.example.mobile.dto.request.CategoryCreationReq;
import com.example.mobile.dto.request.CategoryUpdateReq;
import com.example.mobile.dto.response.ApiResponse;
import com.example.mobile.dto.response.CategoryResponse;
import com.example.mobile.entity.Category;
import com.example.mobile.entity.Notification;
import com.example.mobile.entity.Shop;
import com.example.mobile.entity.Voucher;
import com.example.mobile.exception.AddException;
import com.example.mobile.exception.ErrorCode;
import com.example.mobile.mapper.ICategoryMapper;
import com.example.mobile.repository.CategoryRepository;
import com.example.mobile.repository.ShopRepository;
import com.example.mobile.service.imp.ICategory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CategoryService implements ICategory {
    ICategoryMapper categoryMapper;
    CategoryRepository categoryRepository;
    ShopRepository shopRepository;

    @Override
    public CategoryResponse addCategory(CategoryCreationReq req) {
        Category category = new Category();
        Shop shop = shopRepository.findById(req.getShopId())
                .orElseThrow(() -> new RuntimeException("Shop not found!"));
        category.setName(req.getName());
        category.setShop(shop);
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    @Override
    public List<CategoryResponse> getListCategory() {
        return categoryRepository.findAll().stream().map(categoryMapper::toCategoryResponse).toList();
    }

    @Override
    public CategoryResponse findCategoryById(int id) {
        return categoryMapper.toCategoryResponse(categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found!")));
    }

    @Override
    public CategoryResponse categoryUpdate(int id, CategoryUpdateReq req) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Category not found!"));
        Shop shop = shopRepository.findById(req.getShopId())
                .orElseThrow(() -> new RuntimeException("Shop not found!"));
        category.setName(req.getName());
        category.setShop(shop);
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryResponse findCategoryByName(String name) {
        Category category = categoryRepository.findByName(name).orElseThrow(()-> new RuntimeException("Category not found!"));
        return categoryMapper.toCategoryResponse(category);
    }
}
