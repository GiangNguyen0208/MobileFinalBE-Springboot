package com.example.mobile.dto.request;

import com.example.mobile.constant.FoodStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.awt.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreationReq {
    String name;
    String categoryName;
    String description;
    Double price;
    @Min(1)
    @NotNull
    int quantity;
    int categoryId;


}
