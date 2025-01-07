package com.example.mobile.controller;

import com.example.mobile.dto.request.ShopCreationReq;
import com.example.mobile.dto.request.ShopUpdateReq;
import com.example.mobile.dto.request.UserCreationReq;
import com.example.mobile.dto.request.UserUpdateRequest;
import com.example.mobile.dto.response.ApiResponse;
import com.example.mobile.dto.response.ShopResponse;
import com.example.mobile.dto.response.UserResponse;
import com.example.mobile.service.imp.IShop;
import com.example.mobile.service.imp.IUser;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminController {
    IShop shopService;
    IUser userService;

    // Shop endpoints
    @PostMapping("/shop/add")
    ApiResponse<ShopResponse> createShop(@RequestBody @Valid ShopCreationReq shopCreationReq) {
        ApiResponse<ShopResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(shopService.createShop(shopCreationReq));
        return apiResponse;
    }

    @GetMapping("/shop/list")
    ApiResponse<List<ShopResponse>> getListShop() {
        return ApiResponse.<List<ShopResponse>>builder()
                .result(shopService.getListShop())
                .build();
    }

    @GetMapping("/shop/id/{shopId}")
    ShopResponse getShopById(@PathVariable("shopId") int shopId) {
        return shopService.findShopById(shopId);
    }

    @GetMapping("/shop/name/{shopName}")
    ShopResponse getShopByName(@PathVariable("shopName") String name) {
        return shopService.findShopByName(name);
    }

    @PutMapping("/shop/update/{shopId}")
    ShopResponse updateShop(@PathVariable("shopId") int shopId, @RequestBody ShopUpdateReq shopUpdateReq) {
        return shopService.shopUpdate(shopId, shopUpdateReq);
    }

    @DeleteMapping("/shop/delete/{shopId}")
    String deleteShop(@PathVariable("shopId") int shopId) {
        shopService.deleteShop(shopId);
        return "Shop has been deleted!";
    }

    // User endpoints
    @PostMapping("/user/add")
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationReq userCreationReq) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(userCreationReq));
        return apiResponse;
    }

    @GetMapping("/user/list")
    ApiResponse<List<UserResponse>> getListUser() {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getListUser())
                .build();
    }

    @GetMapping("/user/id/{userId}")
    UserResponse getUserById(@PathVariable("userId") int userId) {
        return userService.findUserById(userId);
    }

    @PutMapping("/user/update/{userId}")
    UserResponse updateUser(@PathVariable("userId") int userId, @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.userUpdate(userId, userUpdateRequest);
    }

    @DeleteMapping("/user/delete/{userId}")
    String deleteUser(@PathVariable("userId") int userId) {
        userService.deleteUser(userId);
        return "User has been deleted!";
    }

}
