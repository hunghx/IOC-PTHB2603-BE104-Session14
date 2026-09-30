package com.microservices.auth.service;

import com.microservices.auth.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.access-token-expiration-ms}")
    private long accessTokenExpirationMs;

    public String generateAccessToken(User user) {
        // TODO: [Lesson 1 & 3]
        // 1. Tạo Claims chứa: roles, userId
        // 2. Ký JWT với HMAC SHA256 và set thời gian hết hạn (ngắn hạn)
        return null;
    }

    public long getRemainingExpirationMs(String token) {
        // TODO: [Lesson 4]
        // 1. Trích xuất thời điểm hết hạn (expiration) từ JWT
        // 2. Tính: ExpirationTime - CurrentTime
        return 0L;
    }

    public Claims extractAllClaims(String token) {
        // TODO: Giải mã chữ ký số và đọc Payload Claims từ JWT
        return null;
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
