package com.example.mobile.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailResponse {
    private Integer id;
    private Integer productId;
    private Double price;
    private Integer quantity;
    private Double amount;
}
