package com.example.mobile.controller;

import com.example.mobile.dto.request.CartItemReq;
import com.example.mobile.dto.response.ApiResponse;
import com.example.mobile.dto.response.CartItemResponse;
import com.example.mobile.service.imp.ICart;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public ApiResponse<List<CartItemReq>> addToCart(
            @RequestBody CartItemReq item,
            HttpSession session) {
        // Lấy giỏ hàng từ session, nếu chưa có thì khởi tạo mới
        List<CartItemReq> cart = (List<CartItemReq>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        // Thêm sản phẩm vào giỏ hàng bằng Service
        List<CartItemReq> updatedCart = cartService.addToCart(item, cart);
        // Lưu lại giỏ hàng mới vào session
        session.setAttribute("cart", updatedCart);
        // Trả về kết quả
        ApiResponse<List<CartItemReq>> response = new ApiResponse<>();
        response.setResult(updatedCart);
        response.setMesg("Add to Cart Successfully!");
        return response;
    }

    @GetMapping("/view")
    public ApiResponse<List<CartItemResponse>> viewCart(HttpSession session) {
        List<CartItemReq> cart = (List<CartItemReq>) session.getAttribute("cart");
        ApiResponse<List<CartItemResponse>> response = new ApiResponse<>();
        if (cart == null) {
            cart = new ArrayList<>();
        }
        List<CartItemResponse> cartDetail = cartService.viewCart(cart);
        response.setResult(cartDetail);
        response.setMesg("View Cart Successfully!");
        return response;
    }

    @PostMapping("clear")
    public ApiResponse<String> clearCart(HttpSession session) {
        List<CartItemReq> cart = (List<CartItemReq>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        cartService.clearCart(cart);
        ApiResponse<String> response = new ApiResponse<>();
        response.setMesg("Cart has been cleared successfully.");
        response.setResult("Success");
        return response;
    }

    @PostMapping("increase/{productId}")
    public ApiResponse<CartItemResponse> increase(@PathVariable int productId, HttpSession session) {
        List<CartItemReq> cart = (List<CartItemReq>) session.getAttribute("cart");
        if (cart == null) {
            throw new RuntimeException("Cart is empty.");
        }
        CartItemResponse updatedItem = cartService.increaseQuantity(productId, cart);
        session.setAttribute("cart", cart); // Cập nhật session
        ApiResponse<CartItemResponse> response = new ApiResponse<>();
        response.setResult(updatedItem);
        response.setMesg("Increased quantity successfully.");
        return response;
    }
    @PostMapping("decrease/{productId}")
    public ApiResponse<CartItemResponse> clearCart(@PathVariable int productId, HttpSession session) {
        List<CartItemReq> cart = (List<CartItemReq>) session.getAttribute("cart");
        if (cart == null) {
            throw new RuntimeException("Cart is empty.");
        }
        CartItemResponse updatedItem = cartService.decreaseQuantity(productId, cart);
        session.setAttribute("cart", cart); // Cập nhật session
        ApiResponse<CartItemResponse> response = new ApiResponse<>();
        response.setResult(updatedItem);
        response.setMesg("Decreased quantity successfully.");
        return response;
    }
}
