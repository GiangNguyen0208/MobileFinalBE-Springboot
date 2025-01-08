package com.example.mobile.dto.request;

import com.example.mobile.dto.response.ImageProductResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductUpdateReq {
    String name;
    private String description;;
    double price;
    int quantity;
    int categoryId;
}
