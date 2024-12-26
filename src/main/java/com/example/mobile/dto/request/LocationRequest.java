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
public class LocationRequest {
    private String location_line1;

    private String location_line2;

    private String city;

    private String district;

    private Long latitude;

    private Long longtitude;

    private String country;
    private int userId;
}
