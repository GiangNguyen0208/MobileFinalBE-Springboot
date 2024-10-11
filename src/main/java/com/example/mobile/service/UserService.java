package com.example.mobile.service;

import com.example.mobile.constant.RolePlay;
import com.example.mobile.dto.RegisterDTO;
import com.example.mobile.dto.UserDTO;
import com.example.mobile.dto.request.UserCreationReq;
import com.example.mobile.dto.request.UserUpdateRequest;
import com.example.mobile.entity.Role;
import com.example.mobile.entity.User;
import com.example.mobile.repository.RoleRepository;
import com.example.mobile.repository.UserRepository;

import com.example.mobile.service.imp.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUser {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository rolesRepository;

//    @Autowired
//    private IPasswordEncode passwordService;

//    @Override
//    public boolean registerUser(RegisterDTO registerDTO) {
//        try {
//            Role role = rolesRepository.findByRoleName(RolePlay.USER) // Truy vấn role USER
//                    .orElseThrow(() -> new RuntimeException("Role not found"));
//            int limitDiscount = 5;
//            Date dob = new Date();
//            User user = User.builder()
//                    .firstname(registerDTO.getFirstName())
//                    .lastname(registerDTO.getLastName())
//                    .password(passwordService.encodePassword(registerDTO.getPassword()))
//                    .email(registerDTO.getEmail())
//                    .role(role)
//                    .phone(registerDTO.getPhone())
//                    .deleted(false)
//                    .status(false)
//                    .limit_discount(limitDiscount)
//                    .createAt(dob)
//                    .build();
//            userRepository.save(user);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    @Override
//    public boolean checkLoginByEmail(String email, String password) {
//        Optional<User> userOptional = userRepository.findByEmail(email);
//
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            return passwordService.matchesPassword(password, user.getPassword());
//        }
//        return false;
//    }
//
//    @Override
//    public UserDTO findUserByPhoneOrEmailOrUsername(String identifyer) {
//        Optional<User> user = userRepository.findAllByUsernameOrEmailOrPhone(identifyer);
//        if (user == null) {
//            return null;
//        }
//        UserDTO userDTO = UserDTO.builder()
//                .firstName(user.get().getFirstname())
//                .lastName(user.get().getLastname())
//                .email(user.get().getEmail())
//                .phone(user.get().getPhone())
//                .password(user.get().getPassword())
//                .phone(user.get().getPhone())
//                .lastName(user.get().getLastname())
//                .createAt(user.get().getCreateAt())
//                .build();
//        return userDTO;
//    }

    @Override
    public User createUser(UserCreationReq req) {
        Date currentTime = new Date();
        if (userRepository.existsByUsername(req.getUsername())) {
            throw new RuntimeException("User existed!");
        }
        User user = User.builder()
                .username(req.getUsername())
                .firstname(req.getFirstName())
                .lastname(req.getLastName())
                .email(req.getEmail())
                .phone(req.getPhone())
                .password(req.getPassword())
                .phone(req.getPhone())
                .createAt(currentTime)
                .build();
        return userRepository.save(user);
    }
    @Override
    public List<User> getListUser() {
        return userRepository.findAll();
    }
    @Override
    public User findUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
    }

    @Override
    public User userUpdate(int id, UserUpdateRequest req) {
        Date currentTime = new Date();
        User user = findUserById(id);
        user.setFirstname(req.getFirstName());
        user.setLastname(req.getLastName());
        user.setPassword(req.getPassword());
        user.setCreateAt(currentTime);

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
