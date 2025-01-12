package com.example.mobile.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {
    int id;
    int idShop;
    int idProduct;
    String name;
    double rating;
    String shopName;
    String status;
    List<String> imageLink;
}
