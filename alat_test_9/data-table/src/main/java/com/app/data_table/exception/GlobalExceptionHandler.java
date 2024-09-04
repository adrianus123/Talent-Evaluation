package com.app.data_table.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.data_table.dto.ApiResponse;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleEntityNotFoundException(EntityNotFoundException ex) {
        ApiResponse<String> response = ApiResponse.<String>builder()
                .data(null)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
