package com.microservices.order.service;

import com.microservices.order.dto.OrderRequest;
import com.microservices.order.dto.OrderResponse;

import java.util.List;

public interface OrderService {
    List<OrderResponse> getAllOrders();
    OrderResponse getOrderById(Long id);
    OrderResponse createOrder(OrderRequest request, String username);
    void deleteOrder(Long id);
}
