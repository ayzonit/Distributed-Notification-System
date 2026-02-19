package com.project.order_service.controllers;

import com.project.order_service.payload.OrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ValidationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<OrderResponse> handleValidationExceptions(MethodArgumentNotValidException m) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : m.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        OrderResponse orderResponse = new OrderResponse(false, "Validation failed", errors);
        return new ResponseEntity<>(orderResponse, HttpStatus.BAD_REQUEST);
    }
}
