package com.example.mobile.service;

import com.example.mobile.dto.request.CartItemReq;
import com.example.mobile.dto.response.CartItemResponse;
import com.example.mobile.entity.Product;
import com.example.mobile.entity.User;
import com.example.mobile.repository.ProductRepository;
import com.example.mobile.repository.UserRepository;
import com.example.mobile.service.imp.ICart;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
            cart.add(new CartItemReq(cartSelected.getProductId(), cartSelected.getUserId(), cartSelected.getQuantity()));
        }
        return cart;
    }
    @Override
    public CartItemResponse decreaseQuantity(int idCartItem) {
        return null;
    }

    @Override
    public CartItemResponse increaseQuantity(int idCartItem) {
        return null;
    }

    CartItemReq
}
