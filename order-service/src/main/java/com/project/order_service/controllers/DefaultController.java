package com.project.order_service.controllers;

import com.project.order_service.payload.OrderResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {
    private static final Logger logger = LoggerFactory.getLogger(DefaultController.class);

    @GetMapping(value = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> healthCheck() {
        OrderResponse orderResponse = new OrderResponse(true, "Service running", null);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @GetMapping("/error")
    public ResponseEntity<OrderResponse> handleError(HttpServletRequest httpServletRequest) {
        Integer statusCode = (Integer) httpServletRequest.getAttribute("jakarta.servlet.error.status_code");
        if(statusCode != null) {
            HttpStatus httpStatus = HttpStatus.resolve(statusCode);
            logger.info("Error encountered with status code: {}", statusCode);

            String message = (String) httpServletRequest.getAttribute("jakarta.servlet.error.message");
            if(httpStatus == HttpStatus.METHOD_NOT_ALLOWED) {
                logger.warn("405 method not allowed - Method does not exist");
                OrderResponse orderResponse = new OrderResponse(false,
                        "405 method not allowed - Method does not exist", null);
                return new ResponseEntity<>(orderResponse, HttpStatus.METHOD_NOT_ALLOWED);
            }
            String errorMessage;
            if(message != null) {
                errorMessage = message;
            }
            else {
                assert httpStatus != null;
                errorMessage = httpStatus.getReasonPhrase();
            }
            OrderResponse orderResponse = new OrderResponse(false, errorMessage, null);
            return new ResponseEntity<>(orderResponse, HttpStatus.valueOf(statusCode));
        }
        logger.error("Unexpected error occurred - Returning 500 Internal Server Error");
        OrderResponse orderResponse = new OrderResponse(false, "An unexpected error occurred", null);
        return new ResponseEntity<>(orderResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
