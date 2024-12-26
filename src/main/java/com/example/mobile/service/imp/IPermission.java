package com.example.mobile.service.imp;

import com.example.mobile.dto.request.PermissionReq;
import com.example.mobile.dto.response.PermissionRes;

import java.util.List;

public interface IPermission {

    PermissionRes create(PermissionReq req);

    List<PermissionRes> getAll();

    void deletedPermissionID(String id);

}
