package com.example.mobile.dto.request;

import com.example.mobile.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationReq {
    private String firstName;
    private String lastName;
    private Date createAt;
    private String phone;

    @Size(min = 6, message = "USERNAME_INVALID")
    private String username;

    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "EMAIL_INVALID")
    private String email;

    @Size(min = 8, message = "PASSWORD_INVALID")
    private String password;
}
