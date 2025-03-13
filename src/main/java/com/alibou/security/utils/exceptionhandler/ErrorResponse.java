package com.alibou.security.utils.exceptionhandler;

// ErrorResponse.java

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String error;
    private String message;
}