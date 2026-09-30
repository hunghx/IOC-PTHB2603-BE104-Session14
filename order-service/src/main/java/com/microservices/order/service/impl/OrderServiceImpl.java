package com.microservices.order.service.impl;

import com.microservices.order.dto.OrderRequest;
import com.microservices.order.dto.OrderResponse;
import com.microservices.order.entity.Order;
import com.microservices.order.repository.OrderRepository;
import com.microservices.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng với ID: " + id));
        return mapToResponse(order);
    }

    @Override
    public OrderResponse createOrder(OrderRequest request, String username) {
        Order order = Order.builder()
            .productName(request.getProductName())
            .price(request.getPrice())
            .username(username)
            .status("CREATED")
            .build();
        return mapToResponse(orderRepository.save(order));
    }

    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy đơn hàng để xóa với ID: " + id);
        }
        orderRepository.deleteById(id);
    }

    private OrderResponse mapToResponse(Order order) {
        return OrderResponse.builder()
            .id(order.getId())
            .productName(order.getProductName())
            .price(order.getPrice())
            .username(order.getUsername())
            .status(order.getStatus())
            .createdAt(order.getCreatedAt())
            .build();
    }
}
