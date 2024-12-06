package com.example.mobile.exception;

import com.example.mobile.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception) {
        ErrorCode errorCode = ErrorCode.UNCATEGORIZED_EXCEPTION;

        ApiResponse apiResponse = ApiResponse.builder()
                .code(errorCode.getCode())
                .mesg(errorCode.getMesg())
                .build();

        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(apiResponse);
    }

    @ExceptionHandler(value = AddException.class)
    ResponseEntity<ApiResponse> handlingAddException(AddException exception) {
        ErrorCode errorCode = exception.getErrorCode();

        ApiResponse apiResponse = ApiResponse.builder()
                .code(errorCode.getCode())
                .mesg(errorCode.getMesg())
                .build();

        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(apiResponse);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse> handlingAccessDeniedException(AddException exception) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;

        ApiResponse apiResponse = ApiResponse.builder()
                .code(errorCode.getCode())
                .mesg(errorCode.getMesg())
                .build();

        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
        ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException exception) {
            String enumkey = exception.getFieldError().getDefaultMessage();
            ErrorCode errorCode = ErrorCode.KEY_INVALID;

            try {errorCode = ErrorCode.valueOf(enumkey);}
            catch (IllegalArgumentException e) { e.getMessage(); }

            ApiResponse apiResponse = ApiResponse.builder()
                    .code(errorCode.getCode())
                    .mesg(errorCode.getMesg())
                    .build();

            return ResponseEntity.status(errorCode
                    .getStatusCode())
                    .body(apiResponse);
    }
}
