package com.example.mobile.controller;

import com.example.mobile.dto.request.CartItemReq;
import com.example.mobile.dto.request.UserCreationReq;
import com.example.mobile.dto.request.UserUpdateRequest;
import com.example.mobile.dto.response.ApiResponse;
import com.example.mobile.dto.response.CartItemResponse;
import com.example.mobile.dto.response.UserResponse;
import com.example.mobile.service.imp.ICart;
import com.example.mobile.service.imp.IUser;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    IUser userService;

    @PostMapping("/register")
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationReq userCreationReq) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(userCreationReq));
        return apiResponse;
    }
    @GetMapping("/getAllUser")
    ApiResponse<List<UserResponse>> getListUser() {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getListUser())
                .build();
    }
    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable("userId") int userId) {
        return userService.findUserById(userId);
    }
    @PutMapping("/update/{userId}")
    UserResponse updateUser(@PathVariable("userId") int userId, @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.userUpdate(userId, userUpdateRequest);
    }
    @DeleteMapping("/delete/{userId}")
    String deleteUser(@PathVariable("userId") int userId) {
        userService.deleteUser(userId);
        return "User has been deleted!";
    }
}
