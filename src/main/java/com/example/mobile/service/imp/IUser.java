package com.example.mobile.service.imp;

import com.example.mobile.dto.RegisterDTO;
import com.example.mobile.dto.UserDTO;
import com.example.mobile.dto.request.UserCreationReq;
import com.example.mobile.dto.request.UserUpdateRequest;
import com.example.mobile.entity.User;

import java.util.List;

public interface IUser {

//    boolean registerUser(RegisterDTO registerDTO);

//    public boolean checkLoginByEmail(String identifyLogin, String password);

//    UserDTO findUserByPhoneOrEmailOrUsername(String identifyLogin);


    // ***********

    User createUser(UserCreationReq req);
    List<User> getListUser();
    User findUserById(int id);
    User userUpdate(int id, UserUpdateRequest req);
    void deleteUser(int id);
}
