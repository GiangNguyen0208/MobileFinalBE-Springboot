package com.example.mobile.service.imp;

import com.example.mobile.dto.request.CartItemReq;
import com.example.mobile.dto.response.CartItemResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface ICart {
    List<CartItemReq> addToCart(CartItemReq item);
    CartItemResponse decreaseQuantity(int productId, List<CartItemReq> cart);
    CartItemResponse increaseQuantity(int productId, List<CartItemReq> cart);
    List<CartItemResponse> viewCart(List<CartItemReq> cart);
    List<CartItemResponse> clearCart(List<CartItemReq> cart);
}
