package com.project.order_service.services;

import com.project.order_service.models.OrderModel;

import java.util.List;

public interface OrderService {

    List<OrderModel> getOrdersByUserId(String userId);
    OrderModel createOrder(OrderModel order);
}
