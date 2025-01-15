package com.example.mobile.service.imp;

import com.example.mobile.dto.request.UserCreationReq;
import com.example.mobile.dto.request.UserUpdateRequest;
import com.example.mobile.dto.response.UserResponse;

import java.util.List;

public interface IUser {
    UserResponse createUser(UserCreationReq req);
    List<UserResponse> getListUser();
    UserResponse findUserById(int id);
    UserResponse userUpdate(int id, UserUpdateRequest req);
    void deleteUser(int id);
    void updateUser(int id, UserUpdateRequest req);

}
