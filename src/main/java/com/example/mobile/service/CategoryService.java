package com.example.mobile.service;

import com.example.mobile.dto.request.CategoryCreationReq;
import com.example.mobile.dto.request.CategoryUpdateReq;
import com.example.mobile.dto.response.CategoryResponse;
import com.example.mobile.entity.Category;
import com.example.mobile.entity.Product;
import com.example.mobile.entity.Shop;
import com.example.mobile.entity.User;
import com.example.mobile.repository.CategoryRepository;
import com.example.mobile.repository.ShopRepository;
import com.example.mobile.repository.UserRepository;
import com.example.mobile.service.imp.ICategory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService  implements ICategory {
    ShopRepository shopRepository;
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> getListCategory(int shopID) {
        List<CategoryResponse> categoryResponseList = new ArrayList<>();
        Shop shop = shopRepository.findById(shopID)
                .orElseThrow(() -> new RuntimeException("Shop not found!"));

        List<Category> listCategories = categoryRepository.findByShopAndDeletedFalse(shop);
        for (Category category : listCategories) {
            CategoryResponse categoryResponse = CategoryResponse.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .shopName(shop.getName())
                    .status(category.getStatus())
                    .build();
            categoryResponseList.add(categoryResponse);
        }
        return  categoryResponseList;
    }

    @Override
    public List<CategoryResponse> getListCategory() {
        List<CategoryResponse> categoryResponseList = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories) {
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
    public CategoryResponse addCategory(CategoryCreationReq req) {
        return null;
    }

    @Override
    public CategoryResponse findCategoryById(int id) {
        return null;
    }

    @Override
    public CategoryResponse categoryUpdate(int id, CategoryUpdateReq req) {
        return null;
    }

    @Override
    public void deleteCategory(int id) {

    }

    @Override
    public CategoryResponse findCategoryByName(String name) {
        return null;
    }
}