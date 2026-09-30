package com.microservices.order.controller;

import com.microservices.order.dto.OrderRequest;
import com.microservices.order.dto.OrderResponse;
import com.microservices.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * API xem tất cả đơn hàng
     * TODO: [Lesson 3] Thêm phân quyền cho USER và ADMIN: @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
     */
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    /**
     * API xem chi tiết 1 đơn hàng
     * TODO: [Lesson 3] Thêm phân quyền cho USER và ADMIN: @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    /**
     * API tạo đơn hàng mới
     * TODO: [Lesson 3] Thêm phân quyền: @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
     */
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest request,
                                                    @AuthenticationPrincipal String username) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(orderService.createOrder(request, username != null ? username : "anonymous"));
    }

    /**
     * API xóa đơn hàng
     * TODO: [Lesson 3] Thêm phân quyền CHỈ DUY NHẤT ADMIN: @PreAuthorize("hasRole('ADMIN')")
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Xoá thành công đơn hàng có ID: " + id);
    }
}
