package com.example.mobile.exception;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    KEY_INVALID(1001, "Invalid message key", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User existed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1003, "User not existed!", HttpStatus.NOT_FOUND),
    USERNAME_INVALID(1004, "Username must be at least 3 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1005, "Password must be at least 8 characters", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(1006, "Email must be correctly syntax", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1007, "Unauthenticated!.", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1008, "You do not have permission!.", HttpStatus.FORBIDDEN),
    SHOP_EXISTED(1009, "Shop existed", HttpStatus.BAD_REQUEST),
    PRODUCT_EXISTED(1010, "Product existed!.", HttpStatus.BAD_REQUEST),
    VOUCHER_EXISTED(1011, "Voucher existed!.", HttpStatus.BAD_REQUEST),
    CATEGORY_EXISTED(1012, "CATEGORY existed!.", HttpStatus.BAD_REQUEST),
    ;

    ErrorCode(int code, String mesg, HttpStatusCode statusCode) {
        this.code = code;
        this.mesg = mesg;
        this.statusCode = statusCode;
    }

    private int code;
    private String mesg;
    private HttpStatusCode statusCode;
}
