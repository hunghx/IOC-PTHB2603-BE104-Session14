package com.microservices.auth.service.impl;

import com.microservices.auth.dto.*;
import com.microservices.auth.entity.RefreshToken;
import com.microservices.auth.entity.Role;
import com.microservices.auth.entity.User;
import com.microservices.auth.repository.RefreshTokenRepository;
import com.microservices.auth.repository.UserRepository;
import com.microservices.auth.service.AuthService;
import com.microservices.auth.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final StringRedisTemplate redisTemplate;

    @Value("${jwt.refresh-token-expiration-ms}")
    private long refreshTokenExpirationMs;

    @Override
    @Transactional
    public TokenResponse login(LoginRequest request) {
        // TODO: [Lesson 1 & 2]
        // 1. Tìm user theo username trong database
        // 2. Kiểm tra mật khẩu (passwordEncoder.matches)
        // 3. Tạo Access Token (Stateless) qua jwtService
        // 4. Tạo & lưu Refresh Token (Stateful) vào database
        // 5. Trả về TokenResponse chứa cả 2 token

        return null;
    }

    @Override
    @Transactional
    public TokenResponse register(RegisterRequest request) {
        // TODO:
        // 1. Kiểm tra username đã tồn tại chưa
        // 2. Tạo User mới và mã hóa mật khẩu
        // 3. Cấp phát cặp Token ban đầu và trả về

        return null;
    }

    @Override
    @Transactional
    public TokenResponse refreshToken(RefreshTokenRequest request) {
        // TODO: [Lesson 2] Triển khai API cấp lại token mới & Cơ chế Xoay vòng (Rotation)
        // 1. Tìm RefreshToken trong Database theo token gửi lên
        // 2. Kiểm tra Replay Attack: Nếu token đã bị thu hồi (revoked == true) -> Thu hồi tất cả token của user
        // 3. Kiểm tra hết hạn: expiryDate so với Instant.now()
        // 4. Token Rotation: Đánh dấu token cũ đã bị thu hồi (revoked = true) và lưu DB
        // 5. Tạo Access Token mới & tạo Refresh Token mới lưu DB
        // 6. Trả về cặp Token mới

        return null;
    }

    @Override
    @Transactional
    public void logout(LogoutRequest request) {
        // TODO: [Lesson 4] Thu hồi Token (Redis Blacklist) và xử lý Đăng xuất
        // 1. Lấy chuỗi Access Token và Refresh Token từ request
        // 2. Tính thời gian sống còn lại (TTL) của Access Token (ms) qua jwtService
        // 3. Nếu TTL > 0: Lưu Access Token vào Redis với key "blacklist:<token>" và set expire = TTL
        // 4. Hủy (Revoke) Refresh Token trong Database
    }

    private RefreshToken createAndSaveRefreshToken(User user) {
        // TODO: [Lesson 2] Hàm phụ trợ tạo RefreshToken entity với UUID ngẫu nhiên và lưu Database
        return null;
    }
}
