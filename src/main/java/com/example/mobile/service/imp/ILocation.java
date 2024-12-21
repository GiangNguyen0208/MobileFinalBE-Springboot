package com.example.mobile.service.imp;

import com.example.mobile.dto.request.LocationRequest;
import com.example.mobile.entity.Location;

public interface ILocation {
    public Location saveLocation (LocationRequest locationRequest);
}
