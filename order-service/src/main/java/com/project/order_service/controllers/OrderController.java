package com.project.order_service.controllers;

import com.project.order_service.models.OrderModel;
import com.project.order_service.payload.OrderResponse;
import com.project.order_service.services.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/{userId}/orders")
@Validated
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping
    public ResponseEntity<OrderResponse> getUserOrders(@NotNull @PathVariable
                                                       @NotBlank(message = "userId is required")
                                                       String userId) {
        List<OrderModel> orders = orderService.getOrdersByUserId(userId);
        OrderResponse orderResponse = new OrderResponse(true, "Orders fetched successfully",
                orders);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }
}
