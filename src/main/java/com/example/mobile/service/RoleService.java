package com.example.mobile.service;

import com.example.mobile.dto.request.RoleReq;
import com.example.mobile.dto.response.RoleRes;
import com.example.mobile.entity.Permissions;
import com.example.mobile.entity.Role;
import com.example.mobile.mapper.IRoleMapper;
import com.example.mobile.repository.PermissionsRepository;
import com.example.mobile.repository.RoleRepository;
import com.example.mobile.service.imp.IRole;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleService implements IRole {
    RoleRepository roleRepository;
    PermissionsRepository permissionsRepository;
    IRoleMapper mapper;
    @Override
    public RoleRes create(RoleReq req) {
        var role = mapper.toRole(req);

        var permissions = permissionsRepository.findAllById(req.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);
        return mapper.toRoleResponse(role);
    }

    @Override
    public List<RoleRes> getAll() {
        return roleRepository.findAll()
                .stream().map(mapper::toRoleResponse)
                .toList();
    }

    @Override
    public void delete(String role) {
        roleRepository.deleteById(role);
    }
}
