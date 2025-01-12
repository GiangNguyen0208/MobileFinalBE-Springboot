package com.example.mobile.controller;

import java.text.ParseException;

import org.springframework.web.bind.annotation.*;

import com.example.mobile.dto.request.AuthenticationReq;
import com.example.mobile.dto.request.IntrospectReq;
import com.example.mobile.dto.response.ApiResponse;
import com.example.mobile.dto.response.AuthenticationRes;
import com.example.mobile.dto.response.IntrospectRes;
import com.example.mobile.service.imp.IAuthentication;
import com.nimbusds.jose.JOSEException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    IAuthentication authenticationService;

    @PostMapping("/token")
    @ResponseBody
    public ApiResponse<AuthenticationRes> authenticate(@RequestBody AuthenticationReq req) {
        var result = authenticationService.authentication(req);
        return ApiResponse.<AuthenticationRes>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectRes> introspect(@RequestBody IntrospectReq req) throws ParseException, JOSEException {
        var result = authenticationService.introspect(req);
        return ApiResponse.<IntrospectRes>builder()
                .result(result)
                .build();
    }

}
