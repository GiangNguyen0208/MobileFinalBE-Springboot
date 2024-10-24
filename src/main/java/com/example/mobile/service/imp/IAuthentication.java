package com.example.mobile.service.imp;

import com.example.mobile.dto.request.AuthenticationReq;
import com.example.mobile.dto.request.IntrospectReq;
import com.example.mobile.dto.response.AuthenticationRes;
import com.example.mobile.dto.response.IntrospectRes;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface IAuthentication {
    AuthenticationRes authentication(AuthenticationReq req);
    IntrospectRes introspect(IntrospectReq req) throws JOSEException, ParseException;
    String generateToken(String username);
}
