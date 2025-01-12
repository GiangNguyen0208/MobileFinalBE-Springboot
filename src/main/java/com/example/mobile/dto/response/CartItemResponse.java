package com.example.mobile.dto.response;

import com.example.mobile.constant.FoodStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemResponse {
    int idProduct;
    int idUser;
    String name;
    String des;
    String image;
    double price;
    double rating;
    int quantity;
    double totalPrice;
}
