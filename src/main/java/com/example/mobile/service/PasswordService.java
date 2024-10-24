package com.example.mobile.service;

import com.example.mobile.service.imp.IPasswordEncode;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService implements IPasswordEncode {
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    @Override
    public String encodePassword(CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
    @Override
    public boolean matchesPassword(CharSequence rawPassword, String encodePassword) {
        return passwordEncoder.encode(rawPassword).matches(encodePassword);
    }
}
