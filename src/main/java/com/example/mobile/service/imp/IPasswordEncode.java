package com.example.mobile.service.imp;

public interface IPasswordEncode {
    String encodePassword(CharSequence rawPassword);
    boolean matchesPassword(CharSequence rawPassword, String encodePassword);
    default boolean upgradeEncoding(String encodePasswowrd) {return false;}
}
