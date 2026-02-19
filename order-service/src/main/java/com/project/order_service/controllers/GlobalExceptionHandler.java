package com.project.order_service.controllers;

import com.project.order_service.payload.OrderResponse;
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
    public ResponseEntity<OrderResponse> handleResponseStatusException(ResponseStatusException e) {
        OrderResponse orderResponse = new OrderResponse(false, e.getReason(), null);
        return new ResponseEntity<>(orderResponse, e.getStatusCode());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<OrderResponse> handleConstraintViolationException(ConstraintViolationException c) {
        String message = c.getConstraintViolations().stream()
                .map(violation -> violation.getMessage())
                .collect(Collectors.joining(", "));
        OrderResponse orderResponse = new OrderResponse(false, message, null);
        return new ResponseEntity<>(orderResponse, HttpStatus.BAD_REQUEST);
    }
}
