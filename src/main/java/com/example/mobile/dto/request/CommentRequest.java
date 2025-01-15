package com.example.mobile.dto.request;

import com.example.mobile.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    private String content;
    private String username;
    private String shopName;
    private String productName;
}
