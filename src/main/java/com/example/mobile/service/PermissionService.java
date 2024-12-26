package com.example.mobile.service;

import com.example.mobile.dto.request.PermissionReq;
import com.example.mobile.dto.response.PermissionRes;
import com.example.mobile.entity.Permissions;
import com.example.mobile.mapper.IPermissionMapper;
import com.example.mobile.repository.PermissionsRepository;
import com.example.mobile.service.imp.IPermission;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionService implements IPermission {
    PermissionsRepository permissionsRepository;
    IPermissionMapper mapper;
    @Override
    public PermissionRes create(PermissionReq req) {
        Permissions permissions = mapper.toPermission(req);
        permissions = permissionsRepository.save(permissions);
        return mapper.toPermissionResponse(permissions);
    }

    @Override
    public List<PermissionRes> getAll() {
        return permissionsRepository.findAll()
                .stream().map(mapper::toPermissionResponse)
                .toList();
    }

    @Override
    public void deletedPermissionID(String id) {
        permissionsRepository.deleteById(id);
    }
}
