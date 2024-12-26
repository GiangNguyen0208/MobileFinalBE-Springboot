package com.example.mobile.mapper;


import com.example.mobile.dto.request.PermissionReq;
import com.example.mobile.dto.request.UserCreationReq;
import com.example.mobile.dto.request.UserUpdateRequest;
import com.example.mobile.dto.response.PermissionRes;
import com.example.mobile.dto.response.UserResponse;
import com.example.mobile.entity.Permissions;
import com.example.mobile.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IPermissionMapper {
    Permissions toPermission(PermissionReq req);
    PermissionRes toPermissionResponse(Permissions permissions);
}
