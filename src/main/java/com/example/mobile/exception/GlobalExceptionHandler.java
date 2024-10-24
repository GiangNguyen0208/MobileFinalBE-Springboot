package com.example.mobile.exception;

import com.example.mobile.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponse.setMesg(ErrorCode.UNCATEGORIZED_EXCEPTION.getMesg());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AddException.class)
    ResponseEntity<ApiResponse> handlingAddException(AddException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMesg(errorCode.getMesg());
        return ResponseEntity.badRequest().body(apiResponse);
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
        ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException exception) {
            String enumkey = exception.getFieldError().getDefaultMessage();
            ErrorCode errorCode = ErrorCode.KEY_INVALID;

            try {
                errorCode = ErrorCode.valueOf(enumkey);
            }   catch (IllegalArgumentException e) {

            }

            ApiResponse apiResponse = new ApiResponse();

            apiResponse.setCode(errorCode.getCode());
            apiResponse.setMesg(errorCode.getMesg());

            return ResponseEntity.badRequest().body(apiResponse);
    }
}
