package com.example.mobile.service;

import com.example.mobile.dto.request.LocationRequest;
import com.example.mobile.entity.Location;
import com.example.mobile.entity.User;
import com.example.mobile.repository.LocationRepository;
import com.example.mobile.service.imp.ILocation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class LocationService implements ILocation {
     LocationRepository locationRepository;
     UserService userService;

    @Override
    public Location saveLocation(LocationRequest locationRequest) {
        User user = userService.getUserById(locationRequest.getUserId());
        Location location = new Location();
        location.setLocation_line1(locationRequest.getLocation_line1());
        location.setLocation_line2(locationRequest.getLocation_line2());
        location.setCity(locationRequest.getCity());
        location.setDistrict(locationRequest.getDistrict());
        location.setLatitude(locationRequest.getLatitude());
        location.setLongtitude(locationRequest.getLongtitude());
        location.setCountry(locationRequest.getCountry());
        location.setUser(user);
        return locationRepository.save(location);
    }
}
