package com.example.mobile.exception;

import com.example.mobile.dto.response.ApiResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(value = DataAccessException.class)
public ResponseEntity<ApiResponse> handleDatabaseException(DataAccessException exception) {
    ErrorCode errorCode = ErrorCode.UNCATEGORIZED_EXCEPTION;

    ApiResponse apiResponse = ApiResponse.builder()
            .code(errorCode.getCode())
            .mesg("Database error: " + exception.getMessage())
            .build();

    return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponse);
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

//    @ExceptionHandler(value = AccessDeniedException.class)
//    ResponseEntity<ApiResponse> handlingAccessDeniedException(AddException exception) {
//        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
//
//        ApiResponse apiResponse = ApiResponse.builder()
//                .code(errorCode.getCode())
//                .mesg(errorCode.getMesg())
//                .build();
//
//        return ResponseEntity
//                .status(errorCode.getStatusCode())
//                .body(apiResponse);
//    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<String> handleRuntimeException(RuntimeException ex) {
        ApiResponse<String> response = new ApiResponse<>();
        response.setMesg(ex.getMessage());
        response.setResult("Error");
        return response;  // Trả về ApiResponse thay vì ResponseEntity
    }

    @ExceptionHandler(value = JwtException.class)
    public ResponseEntity<ApiResponse> handleJwtException(JwtException exception) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;

        ApiResponse apiResponse = ApiResponse.builder()
                .code(errorCode.getCode())
                .mesg(errorCode.getMesg() + ": " + exception.getMessage())
                .build();

        return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponse);
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
