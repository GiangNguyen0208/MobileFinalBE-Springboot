package com.example.mobile.service;

import com.example.mobile.constant.FoodStatus;
import com.example.mobile.dto.request.CartItemReq;
import com.example.mobile.dto.response.CartItemResponse;
import com.example.mobile.entity.Product;
import com.example.mobile.entity.User;
import com.example.mobile.repository.ProductRepository;
import com.example.mobile.repository.UserRepository;
import com.example.mobile.service.imp.ICart;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CartService implements ICart {
    ProductRepository productRepository;
    UserRepository userRepository;
    @Override
    public List<CartItemReq> addToCart(CartItemReq cartSelected, List<CartItemReq> cart) {
        Optional<CartItemReq> existingItem = cart.stream()
                .filter(item ->
                        Objects.equals(item.getProductId(), cartSelected.getProductId()) &&
                                Objects.equals(item.getUserId(), cartSelected.getUserId())
                )
                .findFirst();
        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + cartSelected.getQuantity());
        } else {
            cart.add(cartSelected);
        }
        return cart;
    }
    @Override
    public CartItemResponse increaseQuantity(int productId, List<CartItemReq> cart) {
        for (CartItemReq item : cart) {
            if (item.getProductId() == productId) {
                item.setQuantity(item.getQuantity() + 1);
                Product product = productRepository.findById(productId)
                        .orElseThrow(() -> new RuntimeException("Product not found!"));
                return buildCartItemResponse(item, product);
            }
        }
        throw new RuntimeException("Product not found in cart!");

    }

    @Override
    public CartItemResponse decreaseQuantity(int productId, List<CartItemReq> cart) {
        for (CartItemReq item : cart) {
            if (item.getProductId() == productId) {
                if (item.getQuantity() > 1) {
                    item.setQuantity(item.getQuantity() - 1);
                } else {
                    cart.remove(item); // Xóa sản phẩm khỏi giỏ hàng nếu số lượng là 1
                }
                Product product = productRepository.findById(productId)
                        .orElseThrow(() -> new RuntimeException("Product not found!"));
                return buildCartItemResponse(item, product);
            }
        }
        throw new RuntimeException("Product not found in cart!");
    }

    private CartItemResponse buildCartItemResponse(CartItemReq item, Product product) {
        return CartItemResponse.builder()
                .idUser(item.getUserId())
                .idProduct(item.getProductId())
                .name(product.getName())
                .des("x" + item.getQuantity() + " " + product.getName())
                .price(product.getPrice())
                .quantity(item.getQuantity())
                .totalPrice(product.getPrice() * item.getQuantity())
                .build();
    }

    @Override
    public List<CartItemResponse> viewCart(List<CartItemReq> cart) {
        List<CartItemResponse> cartDetail = new ArrayList<>();
        for (CartItemReq item: cart) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException(("Product not found!")));
            User user = userRepository.findById(item.getUserId())
                    .orElseThrow(() -> new RuntimeException(("Product not found!")));
            String description =  "x" +item.getQuantity()+ " " + product.getName();
            CartItemResponse cartItemResponse = CartItemResponse.builder()
                    .idUser(product.getId())
                    .idProduct(user.getId())
                    .name(product.getName())
                    .des("You has bought: " + description)
                    .price(product.getPrice())
                    .quantity(item.getQuantity())
                    .totalPrice(product.getPrice()* item.getQuantity())
                    .build();
            cartDetail.add(cartItemResponse);
        }
        return cartDetail;
    }

    @Override
    public List<CartItemResponse> clearCart(List<CartItemReq> cart) {
        if (cart == null || cart.isEmpty()) {
            throw new RuntimeException("Cart is already empty.");
        }
        cart.clear();
        List<CartItemResponse> clearedCartResponse = new ArrayList<>();
        clearedCartResponse.add(CartItemResponse.builder()
                .des("Cart has been cleared successfully.")
                .build());
        return clearedCartResponse;
    }

}
