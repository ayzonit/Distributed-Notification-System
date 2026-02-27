package com.project.order_service.controllers;

import com.project.order_service.payload.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiResponse> handleResponseStatusException(ResponseStatusException e) {
        ApiResponse apiResponse = new ApiResponse(false, e.getReason(), null);
        return new ResponseEntity<>(apiResponse, e.getStatusCode());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse> handleConstraintViolationException(ConstraintViolationException c) {
        String message = c.getConstraintViolations().stream()
                .map(violation -> violation.getMessage())
                .collect(Collectors.joining(", "));
        ApiResponse apiResponse = new ApiResponse(false, message, null);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
