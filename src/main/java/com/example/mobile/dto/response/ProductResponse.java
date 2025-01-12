package com.example.mobile.dto.response;

import com.example.mobile.constant.FoodStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    int id;
    int categoryId;
    String name;
    String categoryName;
    Double price;
    String des;
    int position;
    double rating;
    int quantity;
    FoodStatus status;
    List<String> imageLink;
}


