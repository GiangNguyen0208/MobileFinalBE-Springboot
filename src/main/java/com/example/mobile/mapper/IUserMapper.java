package com.example.mobile.mapper;


import com.example.mobile.dto.request.UserCreationReq;
import com.example.mobile.dto.request.UserUpdateRequest;
import com.example.mobile.dto.response.UserResponse;
import com.example.mobile.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    User toUser(UserCreationReq req);
    @Mapping(target = "firstname", ignore = true)   // No mapping field firstname
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest req);
}
