package com.example.mobile.service.imp;

import com.example.mobile.dto.request.CartItemReq;
import com.example.mobile.dto.response.CartItemResponse;

import java.util.List;

public interface ICart {
    List<CartItemReq> addToCart(CartItemReq item, List<CartItemReq> cart);
    CartItemResponse decreaseQuantity(int idCartItem);
    CartItemResponse increaseQuantity(int idCartItem);
}
