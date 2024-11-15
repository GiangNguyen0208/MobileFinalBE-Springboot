package com.example.mobile.service;

import com.example.mobile.constant.RolePlay;
import com.example.mobile.dto.request.UserCreationReq;
import com.example.mobile.dto.request.UserUpdateRequest;
import com.example.mobile.dto.response.UserResponse;
import com.example.mobile.entity.Role;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService implements IUser {

    UserRepository userRepository;
    RoleRepository roleRepository;
    IUserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @Override
    public UserResponse createUser(UserCreationReq req) {
        Date currentTime = new Date();
        if (userRepository.existsByUsername(req.getUsername())) {
            throw new AddException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(req);
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        RolePlay rolePlay = req.getRole() != null ? req.getRole() : RolePlay.USER;
        Role role = roleRepository.findByRoleName(rolePlay)
                        .orElseThrow(() -> new RuntimeException(("Role not found!")));
        user.setRole(role);

        return userMapper.toUserResponse(userRepository.save(user));
    }
    @Override
    public List<UserResponse> getListUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
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
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
