package com.example.mobile.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageProductResponse {
    private String imageName;
    private String type;
    private byte[] imageUrl;
}
