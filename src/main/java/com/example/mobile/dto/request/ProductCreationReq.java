package com.example.mobile.dto.request;

import com.example.mobile.constant.FoodStatus;
import com.example.mobile.dto.response.ImageProductResponse;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.awt.*;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreationReq {
    String name;
    int categoryId;
    String description;
    Double price;
    Double rating;
    int quantity;
    FoodStatus status;
}
