package com.example.mobile.controller;

import com.example.mobile.dto.LoginDTO;
import com.example.mobile.dto.RegisterDTO;
import com.example.mobile.dto.request.UserCreationReq;
import com.example.mobile.dto.request.UserUpdateRequest;
import com.example.mobile.dto.response.ApiResponse;
import com.example.mobile.repository.UserRepository;
import com.example.mobile.service.imp.IUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.mobile.entity.User;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private IUser userService;

    @PostMapping("/create")
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationReq userCreationReq) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(userCreationReq));
        return apiResponse;
    }
    @GetMapping("/listUser")
    List<User> getListUser() {
        return userService.getListUser();
    }
    @GetMapping("/{userId}")
    User getUser(@PathVariable("userId") int userId) {
        return userService.findUserById(userId);
    }
    @PutMapping("/{userId}")
    User updateUser(@PathVariable("userId") int userId, @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.userUpdate(userId, userUpdateRequest);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable("userId") int userId) {
        userService.deleteUser(userId);
        return "User has been deleted!";
    }
//    @PostMapping("/users/register")
//    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO registerDTO) {
//        if (registerDTO.getFirstName().isEmpty() ||
//                registerDTO.getLastName().isEmpty() ||
//                registerDTO.getEmail().isEmpty() ||
//                registerDTO.getPassword().isEmpty() ||
//                registerDTO.getUsername().isEmpty()) {
//            return new ResponseEntity<>("Please complete all field data", HttpStatus.BAD_REQUEST);
//        }
//
//        Optional<User> existingUser = userRepository.findByEmail(registerDTO.getEmail());
//        if (existingUser.isPresent()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email or Username already exists!");
//        }
//
//        boolean isRegistered = userService.registerUser(registerDTO);
//        if (isRegistered) {
//            return ResponseEntity.ok("User registered successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed!");
//        }
//    }
//
//    @PostMapping("/users/login")
//    public ResponseEntity<?> loginUser(@RequestBody LoginDTO userLogin) {
//        if (userLogin.getEmail() == null || userLogin.getPassword() == null) {
//            return ResponseEntity.badRequest().body("Email and password must not be null");
//        }
//
//        boolean isLoggedIn = userService.checkLoginByEmail(userLogin.getEmail(), userLogin.getPassword());
//        if (isLoggedIn) {
//            return ResponseEntity.ok("Login successful");
//        } else {
//            return ResponseEntity.status(401).body("Invalid credentials");
//        }
//    }
}
