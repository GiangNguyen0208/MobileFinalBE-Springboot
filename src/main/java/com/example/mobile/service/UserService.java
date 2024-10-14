package com.example.mobile.service;

import com.example.mobile.dto.request.UserCreationReq;
import com.example.mobile.dto.request.UserUpdateRequest;
import com.example.mobile.dto.response.UserResponse;
import com.example.mobile.entity.User;
import com.example.mobile.exception.AddException;
import com.example.mobile.exception.ErrorCode;
import com.example.mobile.mapper.IUserMapper;
import com.example.mobile.repository.RoleRepository;
import com.example.mobile.repository.UserRepository;

import com.example.mobile.service.imp.IUser;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements IUser {

    UserRepository userRepository;
    RoleRepository rolesRepository;
    IUserMapper userMapper;

    @Override
    public User createUser(UserCreationReq req) {
        Date currentTime = new Date();
        if (userRepository.existsByUsername(req.getUsername())) {
            throw new AddException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(req);
        return userRepository.save(user);
    }
    @Override
    public List<User> getListUser() {
        return userRepository.findAll();
    }
    @Override
    public UserResponse findUserById(int id) {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!")));
    }

    @Override
    public UserResponse userUpdate(int id, UserUpdateRequest req) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        userMapper.updateUser(user, req);   // Use MappingTarget to mapping data update from req (new info) into old info

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
