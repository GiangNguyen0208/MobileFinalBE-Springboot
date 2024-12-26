package com.example.mobile.service.imp;

import com.example.mobile.dto.request.RoleReq;
import com.example.mobile.dto.response.RoleRes;
import com.example.mobile.entity.Role;

import java.util.List;

public interface IRole {
    RoleRes create(RoleReq req);
    List<RoleRes> getAll();
    void delete(String role);
}
