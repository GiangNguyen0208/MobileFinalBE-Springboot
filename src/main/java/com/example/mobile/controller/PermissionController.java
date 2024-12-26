package com.example.mobile.controller;

import com.example.mobile.dto.request.PermissionReq;
import com.example.mobile.dto.request.ProductCreationReq;
import com.example.mobile.dto.response.ApiResponse;
import com.example.mobile.dto.response.IntrospectRes;
import com.example.mobile.dto.response.PermissionRes;
import com.example.mobile.dto.response.ProductResponse;
import com.example.mobile.service.imp.IPermission;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    IPermission permissionService;

    @PostMapping("/create")
    ApiResponse<PermissionRes> create(@RequestBody PermissionReq req) {
        return ApiResponse.<PermissionRes>builder()
                .result(permissionService.create(req))
                .build();
    }

    @GetMapping("/getAll")
    ApiResponse<List<PermissionRes>> getAll() {
        return ApiResponse.<List<PermissionRes>>builder()
                .result(permissionService.getAll())
                .build();
    }
    @DeleteMapping("/{permissionId}")
    ApiResponse<Void> delete(@PathVariable("permissionId") String permissionId) {
        permissionService.deletedPermissionID(permissionId);
        return ApiResponse.<Void>builder().build();
    }

}
