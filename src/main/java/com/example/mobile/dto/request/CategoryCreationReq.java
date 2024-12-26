package com.example.mobile.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryCreationReq {
    String name;
    String status;
    String shopName;
}
