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

    @PostMapping("/addShop")
    ApiResponse<ShopResponse> createShop(@RequestBody @Valid ShopCreationReq shopCreationReq) {
        ApiResponse<ShopResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(shopService.createShop(shopCreationReq));
        return apiResponse;
    }

    @GetMapping("/listShop")
    ApiResponse<List<ShopResponse>> getListShop() {
        return ApiResponse.<List<ShopResponse>>builder()
                .result(shopService.getListShop())
                .build();
    }


    @GetMapping("/{shopId}")
    ShopResponse ShopResponse(@PathVariable("shopId") int shopId) {
        return shopService.findShopById(shopId);
    }

    @GetMapping("/{shopName}")
    ShopResponse ShopResponse(@PathVariable("shopName") String name) {
        return shopService.findShopByName(name);
    }

    @PutMapping("/{shopId}")
    ShopResponse updateShop(@PathVariable("shopId") int shopId, @RequestBody ShopUpdateReq shopUpdateReq) {
        return shopService.shopUpdate(shopId, shopUpdateReq);
    }
    @DeleteMapping("/{shopId}")
    String deleteShop(@PathVariable("shopId") int shopId) {
        shopService.deleteShop(shopId);
        return "Shop has been deleted!";
    }


    //user
    @PostMapping("/addUser")
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationReq userCreationReq) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(userCreationReq));
        return apiResponse;
    }
    @GetMapping("/listUser")
    ApiResponse<List<UserResponse>> getListUser() {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getListUser())
                .build();
    }
    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable("userId") int userId) {
        return userService.findUserById(userId);
    }
    @PutMapping("/{userId}")
    UserResponse updateUser(@PathVariable("userId") int userId, @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.userUpdate(userId, userUpdateRequest);
    }
    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable("userId") int userId) {
        userService.deleteUser(userId);
        return "User has been deleted!";
    }
}
