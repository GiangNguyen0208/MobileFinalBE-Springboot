package com.example.mobile.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    private Integer id;
    private Integer voucherId;
    private Double discount;
    private Integer totalProduct;
    private Double amount;
    private String status;
    private Instant createAt;
    private Integer userId;
    private String username;
    private String fullname;
    private Set<OrderDetailResponse> orderDetails;
}
