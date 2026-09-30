package com.microservices.order.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private Long id;
    private String productName;
    private BigDecimal price;
    private String username;
    private String status;
    private Instant createdAt;
}
