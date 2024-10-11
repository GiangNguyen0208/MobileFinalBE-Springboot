package com.example.mobile.service;

import com.example.mobile.dto.request.AuthenticationReq;
import com.example.mobile.repository.UserRepository;
import com.example.mobile.service.imp.IAuthentication;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService implements IAuthentication {
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean authentication(AuthenticationReq req) {

        return false;
    }
}
