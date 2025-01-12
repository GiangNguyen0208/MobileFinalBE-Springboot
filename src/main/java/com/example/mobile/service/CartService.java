package com.example.mobile.service;

import com.example.mobile.constant.FoodStatus;
import com.example.mobile.dto.request.CartItemReq;
import com.example.mobile.dto.response.ApiResponse;
import com.example.mobile.dto.response.CartItemResponse;
import com.example.mobile.entity.Cart;
import com.example.mobile.entity.Product;
import com.example.mobile.entity.User;
import com.example.mobile.exception.AddException;
import com.example.mobile.exception.ErrorCode;
import com.example.mobile.repository.CartRepository;
import com.example.mobile.repository.ProductRepository;
import com.example.mobile.repository.UserRepository;
import com.example.mobile.service.imp.ICart;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

public CartItemResponse addToCart(CartItemReq cartSelected) {
    try {
        // Tìm sản phẩm trong cơ sở dữ liệu
        Product product = productRepository.findById(cartSelected.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + cartSelected.getProductId()));

        // Lấy người dùng (ví dụ sử dụng id = 1, thay thế bằng logic xác thực thực tế)
        User user = userRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("User not found with ID: 1"));


        // Tìm giỏ hàng của người dùng và sản phẩm
        Cart cart = cartRepository.findByUser_IdAndProduct_Id(user.getId(), cartSelected.getProductId())
                .orElse(new Cart());


        // Cập nhật thông tin giỏ hàng
        cart.setUser(user);
        cart.setProduct(product);
        int currentQuantity = cart.getUser() == null ? 0 : cart.getQuantity();
        cart.setQuantity(currentQuantity + cartSelected.getQuantity());
        cart.setTotalPrice(product.getPrice() * cart.getQuantity());

        // Lưu giỏ hàng vào cơ sở dữ liệu
        cartRepository.save(cart);



        // Tạo và trả về CartItemResponse
        return CartItemResponse.builder()
                .idUser(user.getId())
                .idProduct(product.getId())
                .name(product.getName())
                .des(null)
                .price(product.getPrice())
                .quantity(cart.getQuantity())
                .totalPrice(cart.getTotalPrice())
                .rating(product.getRating()) // Nếu sản phẩm có rating
                .build();
    } catch (RuntimeException e) {
        // Log lỗi và ném lại RuntimeException để xử lý
        throw new RuntimeException("Unexpected error occurred while adding to cart: " + e.getMessage(), e);
    }
}



    public void removeFromCart(int productId) {
        // Lấy người dùng (tạm thời dùng id = 1 trong trường hợp này, bạn có thể thay bằng cách lấy từ session hoặc token)
        User user = userRepository.findById(1).get();

        // Tìm giỏ hàng của người dùng và sản phẩm
        Cart cart = cartRepository.findByUser_IdAndProduct_Id(productId, user.getId())
                .orElseThrow(() -> new RuntimeException("Product not found in cart"));

        // Xóa sản phẩm khỏi giỏ hàng
        cartRepository.delete(cart);  // Xóa sản phẩm khỏi giỏ hàng trong database
    }


//    @Override
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

//    @Override
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
                .idUser(1)
                .idProduct(item.getProductId())
                .name(product.getName())
                .des("x" + item.getQuantity() + " " + product.getName())
                .price(product.getPrice())
                .quantity(item.getQuantity())
                .totalPrice(product.getPrice() * item.getQuantity())
                .build();
    }

//    @Override
public ApiResponse<List<CartItemResponse>> viewCart(int userId) {
    // Lấy tất cả các giỏ hàng của người dùng
    List<Cart> cartList = cartRepository.findAllByUserId(userId);

    // Chuyển các giỏ hàng sang dạng response
    List<CartItemResponse> cartItemResponses = new ArrayList<>();
    for (Cart cart : cartList) {
        Product product = cart.getProduct();
        CartItemResponse response = CartItemResponse.builder()
                .idProduct(product.getId())
                .idUser(cart.getUser().getId())
                .name(product.getName())
                .des("x" + cart.getQuantity() + " " + product.getName())
                .price(product.getPrice())
                .quantity(cart.getQuantity())
                .totalPrice(cart.getTotalPrice())
                .rating(product.getRating()) // Nếu sản phẩm có rating
                .build();
        cartItemResponses.add(response);
    }

    // Trả về API response
    ApiResponse<List<CartItemResponse>> response = new ApiResponse<>();
    response.setResult(cartItemResponses);
    response.setMesg("View Cart Successfully!");
    return response;
}


    @Transactional
    public ApiResponse<String> clearCart(int userId) {
        // Gọi repository để xóa tất cả các bản ghi giỏ hàng của người dùng
        cartRepository.deleteAllByUserId(userId);

        // Trả về phản hồi thành công
        ApiResponse<String> response = new ApiResponse<>();
        response.setResult("Success");
        response.setMesg("Cart has been cleared successfully.");
        return response;
    }


}
