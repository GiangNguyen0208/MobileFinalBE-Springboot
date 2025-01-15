package com.example.mobile.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    int id;
    String firstname;
    String lastname;
    String avatar;
    String phone;
    String username;
    String email;
    String password;
    String address;



}
