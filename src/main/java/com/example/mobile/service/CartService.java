package com.example.mobile.service;

import com.example.mobile.dto.request.CartItemReq;
import com.example.mobile.dto.response.ApiResponse;
import com.example.mobile.dto.response.CartItemResponse;
import com.example.mobile.entity.Cart;
import com.example.mobile.entity.ImageProduct;
import com.example.mobile.entity.Product;
import com.example.mobile.entity.User;
import com.example.mobile.repository.CartRepository;
import com.example.mobile.repository.ImageProductRepository;
import com.example.mobile.repository.ProductRepository;
import com.example.mobile.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ImageProductRepository imageProductRepository;

    public CartService(ProductRepository productRepository, UserRepository userRepository, CartRepository cartRepository, ImageProductRepository imageProductRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.imageProductRepository = imageProductRepository;
    }

    private User getCurrentUser() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    public CartItemResponse addToCart(CartItemReq cartSelected) {
        Product product = productRepository.findById(cartSelected.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + cartSelected.getProductId()));

        User user = getCurrentUser();

        Cart cart = cartRepository.findByUser_IdAndProduct_Id(user.getId(), cartSelected.getProductId())
                .orElse(new Cart());

        cart.setUser(user);
        cart.setProduct(product);
        cart.setQuantity(cartSelected.getQuantity());
        cart.setTotalPrice(product.getPrice() * cart.getQuantity());

        cartRepository.save(cart);

        List<ImageProduct> imageProducts = imageProductRepository.findAllImagesByProductId(cartSelected.getProductId());

        return CartItemResponse.builder()
                .idUser(user.getId())
                .idProduct(product.getId())
                .name(product.getName())
                .image(!imageProducts.isEmpty() ? imageProducts.getFirst().getLinkImage() : null)
                .des(null)
                .price(product.getPrice())
                .quantity(cart.getQuantity())
                .totalPrice(cart.getTotalPrice())
                .rating(product.getRating())
                .build();
    }

    public CartItemResponse updateCart(CartItemReq cartSelected) {
        Product product = productRepository.findById(cartSelected.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + cartSelected.getProductId()));

        User user = getCurrentUser();

        Cart cart = cartRepository.findByUser_IdAndProduct_Id(user.getId(), cartSelected.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found in cart"));

        cart.setQuantity(cartSelected.getQuantity());
        cart.setTotalPrice(product.getPrice() * cart.getQuantity());

        cartRepository.save(cart);


        List<ImageProduct> imageProducts = imageProductRepository.findAllImagesByProductId(cartSelected.getProductId());

        return CartItemResponse.builder()
                .idUser(user.getId())
                .idProduct(product.getId())
                .name(product.getName())
                .image(!imageProducts.isEmpty() ? imageProducts.getFirst().getLinkImage() : null)
                .des(null)
                .price(product.getPrice())
                .quantity(cart.getQuantity())
                .totalPrice(cart.getTotalPrice())
                .rating(product.getRating())
                .build();
    }

    public void removeFromCart(int productId) {
        User user = getCurrentUser();

        Cart cart = cartRepository.findByUser_IdAndProduct_Id(user.getId(), productId)
                .orElseThrow(() -> new RuntimeException("Product not found in cart"));

        cartRepository.delete(cart);
    }

    public ApiResponse<List<CartItemResponse>> viewCart() {
        User user = getCurrentUser();

        List<Cart> cartList = cartRepository.findAllByUserId(user.getId());
        List<CartItemResponse> cartItemResponses = new ArrayList<>();
        for (Cart cart : cartList) {
            Product product = cart.getProduct();

            List<ImageProduct> imageProducts = imageProductRepository.findAllImagesByProductId(product.getId());

            cartItemResponses.add(CartItemResponse.builder()
                    .idProduct(product.getId())
                    .idUser(cart.getUser().getId())
                    .image(!imageProducts.isEmpty() ? imageProducts.getFirst().getLinkImage() : null)
                    .name(product.getName())
                    .des("x" + cart.getQuantity() + " " + product.getName())
                    .price(product.getPrice())
                    .quantity(cart.getQuantity())
                    .totalPrice(cart.getTotalPrice())
                    .rating(product.getRating())
                    .build());
        }

        return ApiResponse.<List<CartItemResponse>>builder()
                .result(cartItemResponses)
                .mesg("View Cart Successfully!")
                .build();
    }

    @Transactional
    public ApiResponse<String> clearCart() {
        User user = getCurrentUser();

        cartRepository.deleteAllByUserId(user.getId());

        return ApiResponse.<String>builder()
                .result("Success")
                .mesg("Cart has been cleared successfully.")
                .build();
    }
}
