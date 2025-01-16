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
    ApiResponse<Void> createShop(@RequestBody @Valid ShopCreationReq shopCreationReq) {
        shopService.createShop(shopCreationReq);
        return  ApiResponse.<Void>builder()
                .mesg("đã cập nhật")
                .build();
    }
    @GetMapping("/shop/list")
    ApiResponse<List<ShopResponse>> getListShop() {
        return ApiResponse.<List<ShopResponse>>builder()
                .result(shopService.getListShop())
                .build();
    }
    @GetMapping("/shop/listClosed")
    ApiResponse<List<ShopResponse>> getListShopClosed() {
        return ApiResponse.<List<ShopResponse>>builder()
                .result(shopService.getListShopClosed())
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
    ApiResponse<Void> updateShop(@PathVariable("shopId") int shopId, @RequestBody ShopUpdateReq shopUpdateReq) {
        shopService.shopUpdate(shopId, shopUpdateReq);
        return  ApiResponse.<Void>builder()
                .mesg("đã cập nhật")
                .build();
    }
    @PutMapping("/shop/updateOpen/{shopId}")
    ApiResponse<Void> updateShopOpen(@PathVariable("shopId") int shopId, @RequestBody ShopUpdateReq shopUpdateReq) {
        shopService.shopUpdateOpen(shopId, shopUpdateReq);
        return  ApiResponse.<Void>builder()
                .mesg("đã mở lại shop")
                .build();
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
    ApiResponse<Void>  updateUser(@PathVariable("userId") int userId, @RequestBody UserUpdateRequest userUpdateRequest) {
        userService.updateUser(userId, userUpdateRequest);
        return ApiResponse.<Void>builder()
                .mesg("đã cập nhật")
                .build();
    }


    @DeleteMapping("/user/delete/{userId}")
    String deleteUser(@PathVariable("userId") int userId) {
        userService.deleteUser(userId);
        return "User has been deleted!";
    }

}
