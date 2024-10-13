package com.example.mobile.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class AddException extends RuntimeException {
    private ErrorCode errorCode;
    public AddException(ErrorCode errorCode) {
        super(errorCode.getMesg());
        this.errorCode = errorCode;
    }
}
