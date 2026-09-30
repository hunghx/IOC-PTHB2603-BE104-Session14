package com.microservices.gateway.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Filter tại API Gateway:
 * - Kiểm tra Blacklist trong Redis (Lesson 4)
 * - Giải mã JWT Claims và chuyển tiếp Headers xuống Service con (Lesson 3)
 */
@Component
public class JwtAuthFilter implements GlobalFilter, Ordered {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private final ReactiveStringRedisTemplate redisTemplate;

    public JwtAuthFilter(ReactiveStringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // TODO: 1. Bỏ qua các public path (/auth/**)

        // TODO: 2. Lấy Authorization Header và trích xuất Access Token (Bearer token)

        // TODO: 3. [Lesson 4] Kiểm tra Token trong Redis Blacklist

        // TODO: 4. [Lesson 3] Giải mã Claims (username, roles) từ JWT

        // TODO: 5. Gắn Claims vào Headers (X-User-Name, X-User-Roles) để chuyển tiếp xuống Service con

        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus, String message) {
        exchange.getResponse().setStatusCode(httpStatus);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
