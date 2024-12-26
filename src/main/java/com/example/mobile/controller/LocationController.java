package com.example.mobile.controller;

import com.example.mobile.dto.request.LocationRequest;
import com.example.mobile.dto.response.ApiResponse;
import com.example.mobile.entity.Location;
import com.example.mobile.repository.LocationRepository;
import com.example.mobile.service.LocationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/location")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LocationController {

     LocationService locationService;
    @PostMapping("/save-location")
    public ApiResponse<Location> saveLocation (@RequestBody LocationRequest locationRequest){
        ApiResponse<Location> api = new ApiResponse<>();
        api.setResult(locationService.saveLocation(locationRequest));
        return api;
    }
}
