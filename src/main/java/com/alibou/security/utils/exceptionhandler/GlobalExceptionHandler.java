package com.alibou.security.common;

import com.alibou.security.utils.exceptionhandler.ErrorResponse;
import com.alibou.security.utils.exceptionhandler.PreconditionFailedException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException ex) {
        ErrorResponse errorResponse = new ErrorResponse("NULL_POINTER_EXCEPTION", "A null value was encountered where it is not allowed.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(PreconditionFailedException.class)
    public ResponseEntity<ErrorResponse> handlePreconditionFailedException(PreconditionFailedException ex) {
        ErrorResponse errorResponse = new ErrorResponse("PRECONDITION_FAILED", ex.getMessage());
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(errorResponse); // Fixed Status Code
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("INTERNAL_SERVER_ERROR", "An unexpected error occurred.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
