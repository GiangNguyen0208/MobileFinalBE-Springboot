package com.example.mobile.dto.response;

import com.example.mobile.entity.Permissions;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleRes {
    String name;
    String description;
    Set<PermissionRes> permissions;
}
