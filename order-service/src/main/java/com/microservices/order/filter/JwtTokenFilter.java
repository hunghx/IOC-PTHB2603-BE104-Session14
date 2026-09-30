package com.microservices.order.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filter tại Service con (Resource Server):
 * TODO: [Lesson 3]
 * 1. Trích xuất Role & Username từ Header do API Gateway chuyển tiếp xuống (X-User-Roles, X-User-Name)
 * 2. Hoặc giải mã trực tiếp từ Bearer JWT Header
 * 3. Chuyển đổi Roles sang GrantedAuthority (ROLE_USER, ROLE_ADMIN)
 * 4. Nạp Authentication vào SecurityContextHolder.getContext().setAuthentication(...)
 */
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // TODO: [Lesson 3] Viết logic trích xuất Role & nạp Authentication vào SecurityContext tại đây

        filterChain.doFilter(request, response);
    }
}
