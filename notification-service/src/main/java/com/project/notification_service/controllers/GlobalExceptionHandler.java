package com.project.notification_service.controllers;

import com.project.notification_service.payload.APIResponse;
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
    public ResponseEntity<APIResponse> handleResponseStatusException(ResponseStatusException e) {
        APIResponse apiResponse = new APIResponse(false, e.getReason(), null);
        return new ResponseEntity<>(apiResponse, e.getStatusCode());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponse> handleConstraintViolationException(ConstraintViolationException c) {
        String message = c.getConstraintViolations().stream()
                .map(violation -> violation.getMessage())
                .collect(Collectors.joining(", "));
        APIResponse apiResponse = new APIResponse(false, message, null);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
