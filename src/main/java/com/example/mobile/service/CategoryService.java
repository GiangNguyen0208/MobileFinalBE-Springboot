package com.example.mobile.service;

import com.example.mobile.configuration.ImageUpload;
import com.example.mobile.dto.request.CategoryCreationReq;
import com.example.mobile.dto.request.CategoryUpdateReq;
import com.example.mobile.dto.response.CategoryResponse;
import com.example.mobile.dto.response.ProductResponse;
import com.example.mobile.entity.*;
import com.example.mobile.mapper.ICategoryMapper;
import com.example.mobile.repository.CategoryRepository;
import com.example.mobile.repository.ProductRepository;
import com.example.mobile.repository.ShopRepository;
import com.example.mobile.repository.UserRepository;
import com.example.mobile.service.imp.ICategory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CategoryService  implements ICategory {
    ShopRepository shopRepository;
    ICategoryMapper categoryMapper;
    CategoryRepository categoryRepository;
    ProductRepository productRepository;

    @Override
    public List<CategoryResponse> getListCategoryByShopId(int shopID) {
        List<CategoryResponse> categoryResponseList = new ArrayList<>();
        Shop shop = shopRepository.findById(shopID)
                .orElseThrow(() -> new RuntimeException("Shop not found!"));
        List<Category> listCategories = categoryRepository.findAllByShopAndDeletedFalse(shop);
        for (Category category : listCategories) {
            CategoryResponse categoryResponse = CategoryResponse.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .status(category.getStatus())
                    .build();
            categoryResponseList.add(categoryResponse);
        }
        return  categoryResponseList;
    }

    @Override
    public List<CategoryResponse> getListCategory() {
        List<Category> categories = categoryRepository.findAllActiveCategories();
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for (Category category : categories) {
            CategoryResponse response = CategoryResponse.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .status(category.getStatus())
                    .build();
            categoryResponses.add(response);
        }
        return categoryResponses;
    }

    @Override
    public CategoryResponse addCategory(CategoryCreationReq req) {
        Shop shop = shopRepository.findById(req.getShopId()).orElseThrow(()->new RuntimeException("Shop not found"));
        Category category = categoryMapper.toCategory(req);
        category.setShop(shop);
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    @Override
    public CategoryResponse findCategoryById(int id) {
        return categoryMapper.toCategoryResponse(categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found!")));
    }

    @Override
    public CategoryResponse categoryUpdate(int id, CategoryUpdateReq req) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Category not found!"));
        categoryMapper.updateCategory(category,req);
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(int id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Category not found!"));
        category.setDeleted(true);
        categoryRepository.save(category);
    }

    @Override
    public CategoryResponse findCategoryByName(String name) {
        Category category = categoryRepository.findByName(name).orElseThrow(()-> new RuntimeException("Category not found!"));
        return categoryMapper.toCategoryResponse(category);
    }
}