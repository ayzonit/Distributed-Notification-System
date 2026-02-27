package com.project.order_service.controllers;

import com.project.order_service.models.OrderModel;
import com.project.order_service.payload.OrderRequest;
import com.project.order_service.payload.ApiResponse;
import com.project.order_service.payload.OrderResponse;
import com.project.order_service.services.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/{userId}/orders")
@Validated
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getUserOrders(@PathVariable
                                                       @NotBlank(message = "userId is required")
                                                       String userId) {
        List<OrderModel> orders = orderService.getOrdersByUserId(userId);
        List<OrderResponse> orderResponseDTO = orders.stream()
                .map(orderModel -> modelMapper.map(orderModel, OrderResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(new ApiResponse<>(true, "Orders fetched successfully",
                orderResponseDTO), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> createNewOrder(@PathVariable
                                                                         @NotBlank(message = "userId is required")
                                                                         String userId,
                                                                     @Valid @RequestBody OrderRequest orderRequest) {
        OrderModel order = modelMapper.map(orderRequest, OrderModel.class);
        order.setUserId(userId);
        OrderModel newOrder = orderService.createOrder(order);
        OrderResponse orderResponse = modelMapper.map(newOrder, OrderResponse.class);
        return new ResponseEntity<>(new ApiResponse<>(true, "Order created succesfully",
                orderResponse), HttpStatus.CREATED);
    }
}
