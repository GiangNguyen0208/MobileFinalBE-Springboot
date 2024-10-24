package com.example.mobile.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error"),
    KEY_INVALID(1001, "Invalid message key"),
    USER_EXISTED(1002, "User existed"),
    USER_NOT_EXISTED(1003, "User not existed!"),
    USERNAME_INVALID(1004, "Username must be at least 3 characters"),
    PASSWORD_INVALID(1005, "Password must be at least 8 characters"),
    EMAIL_INVALID(1006, "Email must be correctly syntax"),
    UNAUTHENTICATED(1007, "Unauthenticated!.");

    private int code;
    private String mesg;

    ErrorCode(int code, String mesg) {
        this.code = code;
        this.mesg = mesg;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMesg() {
        return mesg;
    }

    public void setMesg(String mesg) {
        this.mesg = mesg;
    }
}
