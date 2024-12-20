package com.example.mobile.controller;

import com.example.mobile.dto.request.CartItemReq;
import com.example.mobile.dto.response.ApiResponse;
import com.example.mobile.dto.response.CartItemResponse;
import com.example.mobile.service.imp.ICart;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@SessionAttributes("cart")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartController {
    ICart cartService;

    @ModelAttribute("cart")
    public List<CartItemResponse> initCart() {
        return new ArrayList<>();
    }
    @PostMapping("/add")
    ApiResponse<List<CartItemReq>> addToCart(@RequestBody CartItemReq item, @ModelAttribute("cart") List<CartItemReq> cart) {
        ApiResponse<List<CartItemReq>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(cartService.addToCart(item, cart));
        return apiResponse;
    }

}
