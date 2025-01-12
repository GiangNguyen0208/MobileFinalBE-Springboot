package com.example.mobile.controller;

import com.example.mobile.dto.request.PermissionReq;
import com.example.mobile.dto.request.RoleReq;
import com.example.mobile.dto.response.ApiResponse;
import com.example.mobile.dto.response.PermissionRes;
import com.example.mobile.dto.response.RoleRes;
import com.example.mobile.service.imp.IPermission;
import com.example.mobile.service.imp.IRole;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    IRole roleService;

    @PostMapping
    ApiResponse<RoleRes> create(@RequestBody RoleReq req) {
        return ApiResponse.<RoleRes>builder()
                .result(roleService.create(req))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleRes>> getAll() {
        return ApiResponse.<List<RoleRes>>builder()
                .result(roleService.getAll())
                .build();
    }
    @DeleteMapping("/{role}")
    ApiResponse<Void> delete(@PathVariable("role") String role) {
        roleService.delete(role);
        return ApiResponse.<Void>builder().build();
    }

}
