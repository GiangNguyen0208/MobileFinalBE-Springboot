package com.example.mobile.mapper;


import com.example.mobile.dto.request.PermissionReq;
import com.example.mobile.dto.request.RoleReq;
import com.example.mobile.dto.response.PermissionRes;
import com.example.mobile.dto.response.RoleRes;
import com.example.mobile.entity.Permissions;
import com.example.mobile.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IRoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleReq req);
    RoleRes toRoleResponse(Role role);
}
