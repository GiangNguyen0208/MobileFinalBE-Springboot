package com.example.mobile.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    private String firstName;
    private String lastName;
    private String phone;
    private Date createAt;

    @Size(min = 8, message = "Password must be at least 8 characters!")
    private String password;
}
