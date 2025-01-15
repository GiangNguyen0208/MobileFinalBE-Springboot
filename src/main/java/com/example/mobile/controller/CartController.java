package com.example.mobile.controller;

import com.example.mobile.dto.request.CartItemReq;
import com.example.mobile.dto.response.ApiResponse;
import com.example.mobile.dto.response.CartItemResponse;
import com.example.mobile.exception.AddException;
import com.example.mobile.service.CartService;
import com.example.mobile.service.imp.ICart;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartController {
    CartService cartService;

    @ModelAttribute("cart")
    public List<CartItemResponse> initCart() {
        return new ArrayList<>();
    }

    @PostMapping("/add")
    public ApiResponse<?> addToCart(@RequestBody CartItemReq item) {
        ApiResponse<CartItemResponse> response = ApiResponse.<CartItemResponse>builder().build();
        try {
            // Log dữ liệu đầu vào để xem có gì bất thường không
            System.out.println("Adding to cart: " + item);

            // Thêm sản phẩm vào giỏ hàng
            CartItemResponse cartItemResponse = cartService.addToCart(item);
            response.setResult(cartItemResponse);
            response.setMesg("Add to Cart Successfully!");
            return response;
        } catch (RuntimeException e) {
            System.out.println("RuntimeException: " + e.getMessage());  // Log thêm chi tiết lỗi
            response.setMesg("Error: " + e.getMessage());
            response.setCode(400);  // Mã lỗi 400 cho lỗi thông thường
            return response;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());  // Log thêm chi tiết lỗi

            response.setMesg("Unexpected Error Occurred!");
            response.setCode(500);  // Mã lỗi 500 cho lỗi không xác định
            return response;
        }
    }

    @PutMapping("/update")
    public ApiResponse<?> updateCart(@RequestBody CartItemReq item) {
        ApiResponse<CartItemResponse> response = ApiResponse.<CartItemResponse>builder().build();
        try {
            // Log dữ liệu đầu vào để xem có gì bất thường không
            System.out.println("Adding to cart: " + item);

            // Thêm sản phẩm vào giỏ hàng
            CartItemResponse cartItemResponse = cartService.addToCart(item);
            response.setResult(cartItemResponse);
            response.setMesg("Add to Cart Successfully!");
            return response;
        } catch (RuntimeException e) {
            System.out.println("RuntimeException: " + e.getMessage());  // Log thêm chi tiết lỗi
            response.setMesg("Error: " + e.getMessage());
            response.setCode(400);  // Mã lỗi 400 cho lỗi thông thường
            return response;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());  // Log thêm chi tiết lỗi

            response.setMesg("Unexpected Error Occurred!");
            response.setCode(500);  // Mã lỗi 500 cho lỗi không xác định
            return response;
        }
    }




    @PostMapping("/remove/{productId}")
    public ApiResponse<String> removeFromCart(@PathVariable int productId) {
        ApiResponse<String> response = new ApiResponse<>();
        try {
            // Xóa sản phẩm khỏi giỏ hàng
            cartService.removeFromCart(productId);

            // Trả về phản hồi sau khi xóa thành công
            response.setResult("Success");
            response.setMesg("Product removed from cart successfully!");
        } catch (RuntimeException e) {
            // Trường hợp lỗi nếu không tìm thấy sản phẩm trong giỏ hàng
            response.setResult("Failure");
            response.setMesg("Error: " + e.getMessage());
        } catch (Exception e) {
            // Trường hợp lỗi chung khác
            response.setResult("Failure");
            response.setMesg("An unexpected error occurred: " + e.getMessage());
        }
        return response;
    }


    @GetMapping("/view")
    public ApiResponse<List<CartItemResponse>> viewCart() {
        try {
            // Gọi service để lấy danh sách giỏ hàng của người dùng
            ApiResponse<List<CartItemResponse>> response = cartService.viewCart();
            return response;
        } catch (Exception e) {
            // Xử lý lỗi nếu có
            ApiResponse<List<CartItemResponse>> errorResponse = new ApiResponse<>();
            errorResponse.setResult(null);
            errorResponse.setMesg("Failed to view cart: " + e.getMessage());
            return errorResponse;
        }
    }


    @PostMapping("/clear")
    public ApiResponse<String> clearCart() {
        try {
            // Gọi service để xóa giỏ hàng
            ApiResponse<String> response = cartService.clearCart();
            return response;
        } catch (Exception e) {
            // Xử lý lỗi nếu có
            ApiResponse<String> errorResponse = new ApiResponse<>();
            errorResponse.setResult("Error");
            errorResponse.setMesg("Failed to clear cart: " + e.getMessage());
            return errorResponse;
        }
    }
//
//    @PostMapping("increase/{productId}")
//    public ApiResponse<CartItemResponse> increase(@PathVariable int productId, HttpSession session) {
//        List<CartItemReq> cart = (List<CartItemReq>) session.getAttribute("cart");
//        if (cart == null) {
//            throw new RuntimeException("Cart is empty.");
//        }
//        CartItemResponse updatedItem = cartService.increaseQuantity(productId, cart);
//        session.setAttribute("cart", cart); // Cập nhật session
//        ApiResponse<CartItemResponse> response = new ApiResponse<>();
//        response.setResult(updatedItem);
//        response.setMesg("Increased quantity successfully.");
//        return response;
//    }
//    @PostMapping("decrease/{productId}")
//    public ApiResponse<CartItemResponse> clearCart(@PathVariable int productId, HttpSession session) {
//        List<CartItemReq> cart = (List<CartItemReq>) session.getAttribute("cart");
//        if (cart == null) {
//            throw new RuntimeException("Cart is empty.");
//        }
//        CartItemResponse updatedItem = cartService.decreaseQuantity(productId, cart);
//        session.setAttribute("cart", cart); // Cập nhật session
//        ApiResponse<CartItemResponse> response = new ApiResponse<>();
//        response.setResult(updatedItem);
//        response.setMesg("Decreased quantity successfully.");
//        return response;
//    }
}
