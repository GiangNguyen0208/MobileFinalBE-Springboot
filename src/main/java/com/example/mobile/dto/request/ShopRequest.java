package com.example.mobile.dto.request;

import com.example.mobile.constant.StatusShop;
import com.example.mobile.entity.User;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShopRequest {
    private String name;
    private String address;
    private StatusShop status;
    private double rating;
    private int userId;

}
