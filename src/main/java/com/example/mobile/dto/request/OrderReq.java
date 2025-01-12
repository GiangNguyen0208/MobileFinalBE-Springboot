package com.example.mobile.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderReq {
    private Integer voucherId;

    @NotNull
    private Double discount;

    @NotNull
    private Integer totalProduct;

    @NotNull
    private Double amount;

    private String status;

    private Instant createAt;

    private List<OrderDetailReq> orderDetails;
}
