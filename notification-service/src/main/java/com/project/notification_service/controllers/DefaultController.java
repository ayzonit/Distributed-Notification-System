package com.project.notification_service.controllers;

import com.project.notification_service.payload.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController{
    private static final Logger logger = LoggerFactory.getLogger(DefaultController.class);

    @GetMapping(value = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<APIResponse> healthCheck() {
        APIResponse apiResponse = new APIResponse(true, "Service running", null);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}

