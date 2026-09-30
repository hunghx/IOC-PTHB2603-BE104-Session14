# SLIDE BÀI GIẢNG: SESSION 14 - REFRESH TOKEN, PHÂN QUYỀN & THU HỒI TOKEN
**Chủ đề:** Phát triển Hệ thống Microservices với Spring Boot  

---

## 🎯 CÁCH SỬ DỤNG BỘ SLIDE INTERACTIVE

Bạn có thể mở trực tiếp file [index.html](file:///d:/Refreshtoken/index.html) bằng bất kỳ trình duyệt nào (Chrome, Edge, Firefox, Brave,...).

### ⌨️ Phím tắt khi trình chiếu:
- **`→` / `Space` / `PageDown`:** Chuyển sang Slide kế tiếp
- **`←` / `PageUp`:** Quay lại Slide trước
- **`F`:** Bật / Tắt chế độ Toàn màn hình (Fullscreen Presentation)
- **`O`:** Mở danh sách Tổng quan (Grid Overview) để nhảy nhanh đến bất kỳ Slide nào
- **`Home` / `End`:** Về Slide đầu / Nhảy đến Slide cuối
- **Widget Đồng hồ 90 phút (Góc trên):** Bấm nút Play/Pause để quản lý thời gian giảng dạy thực tế.

---

## 📚 CẤU TRÚC 12 SLIDES TẬP TRUNG 4 BÀI HỌC

1. **Slide 1:** Tiêu đề & Giới thiệu tổng quan hệ thống bảo mật Microservices.
2. **Slide 2:** Phân bổ Timeline 90 phút theo 4 Lesson trong chương trình.
3. **Slide 3 (Lesson 1):** Nghịch lý Stateless & Rủi ro bảo mật của Access Token dài hạn.
4. **Slide 4 (Lesson 1):** Sơ đồ cơ chế Dual-Token & Refresh Token Rotation chống Replay Attack.
5. **Slide 5 (Lesson 2):** Thiết kế Database Entity và Endpoint `/auth/refresh-token`.
6. **Slide 6 (Lesson 2):** **Interactive Live Demo Postman:** Giả lập trực quan luồng cấp lại Token.
7. **Slide 7 (Lesson 3):** Mô hình Phân quyền (RBAC / PBAC) đa tầng trong Microservices.
8. **Slide 8 (Lesson 3):** Cấu hình Spring Security Resource Server & `@PreAuthorize` tại Service con.
9. **Slide 9 (Lesson 4):** Vấn đề Đăng xuất của Stateless JWT và giải pháp Redis Blacklist.
10. **Slide 10 (Lesson 4):** Code mẫu LogoutService lưu Redis với TTL động tự hủy bộ nhớ.
11. **Slide 11 (Lesson 4):** **Live Demo Terminal Redis CLI & Postman** chặn token sau khi Logout.
12. **Slide 12:** Tổng kết kiến trúc bảo mật chuẩn công nghiệp & Q&A kết thúc môn.
