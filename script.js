document.addEventListener('DOMContentLoaded', () => {
    const slides = document.querySelectorAll('.slide');
    const totalSlides = slides.length;
    let currentSlide = 1;

    // Controls
    const prevBtn = document.getElementById('prevBtn');
    const nextBtn = document.getElementById('nextBtn');
    const slideIndicator = document.getElementById('slideIndicator');
    const progressBar = document.getElementById('progressBar');
    const btnFullscreen = document.getElementById('btnFullscreen');
    const btnOverview = document.getElementById('btnOverview');
    const overviewModal = document.getElementById('overviewModal');
    const closeOverviewBtn = document.getElementById('closeOverviewBtn');
    const overviewGrid = document.getElementById('overviewGrid');
    const headerSlideTitle = document.getElementById('headerSlideTitle');

    // Slide Titles Map for Header Breadcrumb
    const slideTitles = [
        "Giới thiệu Kiến trúc Bảo mật Microservices",
        "Nội dung & 4 Trụ cột trọng tâm",
        "Lesson 1: Vấn đề bảo mật của Access Token",
        "Lesson 1: Sơ đồ Refresh Token Rotation",
        "Lesson 2: Thiết kế Database & Refresh API",
        "Lesson 2: Trình giả lập Test luồng Postman",
        "Lesson 3: Mô hình Phân quyền Đa tầng Microservices",
        "Lesson 3: Cấu hình Spring Security @PreAuthorize",
        "Lesson 4: Nghịch lý Logout JWT & Giải pháp Redis",
        "Lesson 4: Tích hợp Redis Blacklist với TTL Động",
        "Lesson 4: Live Demo Redis CLI & Postman 401 Interception",
        "Tổng kết Toàn cảnh & Q&A Kết thúc môn"
    ];

    // Initialize Slide UI
    function showSlide(index) {
        if (index < 1) index = 1;
        if (index > totalSlides) index = totalSlides;

        currentSlide = index;

        slides.forEach((slide, idx) => {
            if (idx + 1 === currentSlide) {
                slide.classList.add('active');
            } else {
                slide.classList.remove('active');
            }
        });

        // Update indicator & Progress bar
        slideIndicator.textContent = `Slide ${currentSlide} / ${totalSlides}`;
        const progressPercentage = (currentSlide / totalSlides) * 100;
        progressBar.style.width = `${progressPercentage}%`;

        // Update header topic title
        if (headerSlideTitle && slideTitles[currentSlide - 1]) {
            headerSlideTitle.textContent = slideTitles[currentSlide - 1];
        }

        // Update overview active state
        document.querySelectorAll('.overview-thumb').forEach((thumb, idx) => {
            thumb.classList.toggle('active', idx + 1 === currentSlide);
        });
    }

    function nextSlide() {
        if (currentSlide < totalSlides) {
            showSlide(currentSlide + 1);
        }
    }

    function prevSlide() {
        if (currentSlide > 1) {
            showSlide(currentSlide - 1);
        }
    }

    // Event Listeners for Nav buttons
    prevBtn.addEventListener('click', prevSlide);
    nextBtn.addEventListener('click', nextSlide);

    // Keyboard Navigation
    document.addEventListener('keydown', (e) => {
        if (overviewModal.classList.contains('open')) {
            if (e.key === 'Escape' || e.key.toLowerCase() === 'o') {
                toggleOverview(false);
            }
            return;
        }

        switch (e.key) {
            case 'ArrowRight':
            case 'ArrowDown':
            case ' ':
            case 'PageDown':
                e.preventDefault();
                nextSlide();
                break;
            case 'ArrowLeft':
            case 'ArrowUp':
            case 'PageUp':
                e.preventDefault();
                prevSlide();
                break;
            case 'Home':
                e.preventDefault();
                showSlide(1);
                break;
            case 'End':
                e.preventDefault();
                showSlide(totalSlides);
                break;
            case 'f':
            case 'F':
                toggleFullscreen();
                break;
            case 'o':
            case 'O':
                toggleOverview(true);
                break;
            case 'Escape':
                if (document.fullscreenElement) {
                    document.exitFullscreen();
                }
                break;
        }
    });

    // Fullscreen Toggle
    function toggleFullscreen() {
        if (!document.fullscreenElement) {
            document.documentElement.requestFullscreen().catch((err) => {
                console.warn(`Error attempting to enable fullscreen: ${err.message}`);
            });
            btnFullscreen.innerHTML = '<i class="fa-solid fa-compress"></i>';
        } else {
            if (document.exitFullscreen) {
                document.exitFullscreen();
                btnFullscreen.innerHTML = '<i class="fa-solid fa-expand"></i>';
            }
        }
    }

    btnFullscreen.addEventListener('click', toggleFullscreen);

    // Overview Mode
    function populateOverview() {
        overviewGrid.innerHTML = '';
        slides.forEach((slide, idx) => {
            const slideNum = idx + 1;
            const heading = slideTitles[idx] || `Slide ${slideNum}`;
            
            const thumb = document.createElement('div');
            thumb.className = `overview-thumb ${slideNum === currentSlide ? 'active' : ''}`;
            thumb.innerHTML = `
                <span class="thumb-number">SLIDE ${slideNum}</span>
                <div class="thumb-title">${heading}</div>
            `;
            thumb.addEventListener('click', () => {
                showSlide(slideNum);
                toggleOverview(false);
            });
            overviewGrid.appendChild(thumb);
        });
    }

    function toggleOverview(open) {
        if (open) {
            populateOverview();
            overviewModal.classList.add('open');
        } else {
            overviewModal.classList.remove('open');
        }
    }

    btnOverview.addEventListener('click', () => toggleOverview(true));
    closeOverviewBtn.addEventListener('click', () => toggleOverview(false));
    overviewModal.addEventListener('click', (e) => {
        if (e.target === overviewModal) toggleOverview(false);
    });

    // Interactive Postman Simulator (Slide 6)
    const mockData = {
        'login': {
            method: 'POST',
            url: 'https://api.microservices.local/auth/login',
            status: 'Status: 200 OK',
            statusClass: 'status-200',
            req: `{\n  "username": "sinhvien_it",\n  "password": "Password@123"\n}`,
            res: `{\n  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzaW5odmllbl9pdCIsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE3Mjc3MDEyMDAsImV4cCI6MTcyNzcwMTIzMH0...",\n  "refreshToken": "4f9d8a21-9e7b-41c3-8f0b-193c68df419a",\n  "tokenType": "Bearer",\n  "expiresIn": 30\n}`
        },
        'call-expired': {
            method: 'GET',
            url: 'https://api.microservices.local/api/orders',
            status: 'Status: 401 Unauthorized',
            statusClass: 'status-401',
            req: `Headers:\nAuthorization: Bearer <AccessToken_30s_đã_hết_hạn>`,
            res: `{\n  "timestamp": "2026-09-30T16:15:35Z",\n  "status": 401,\n  "error": "Unauthorized",\n  "message": "JWT Token đã hết hạn! Vui lòng cấp lại token mới."\n}`
        },
        'refresh-call': {
            method: 'POST',
            url: 'https://api.microservices.local/auth/refresh-token',
            status: 'Status: 200 OK',
            statusClass: 'status-200',
            req: `{\n  "refreshToken": "4f9d8a21-9e7b-41c3-8f0b-193c68df419a"\n}`,
            res: `{\n  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzaW5odmllbl9pdCIsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE3Mjc3MDEyNjAsImV4cCI6MTcyNzcwMTI5MH0...",\n  "refreshToken": "7c2a1e89-6b5d-4f12-a39c-882d99ef712b",\n  "tokenType": "Bearer",\n  "expiresIn": 30\n}`
        },
        'call-success': {
            method: 'GET',
            url: 'https://api.microservices.local/api/orders',
            status: 'Status: 200 OK',
            statusClass: 'status-200',
            req: `Headers:\nAuthorization: Bearer <AccessToken_MỚI_vừa_cấp>`,
            res: `[\n  {\n    "id": 101,\n    "product": "Khóa học Microservices Spring Boot",\n    "price": 2500000,\n    "status": "COMPLETED"\n  }\n]`
        }
    };

    const stepItems = document.querySelectorAll('.p-step');
    const mockMethod = document.getElementById('mockMethod');
    const mockUrl = document.getElementById('mockUrl');
    const mockStatus = document.getElementById('mockStatus');
    const mockReqBody = document.getElementById('mockReqBody');
    const mockResBody = document.getElementById('mockResBody');

    stepItems.forEach(item => {
        item.addEventListener('click', () => {
            stepItems.forEach(i => i.classList.remove('active'));
            item.classList.add('active');

            const key = item.getAttribute('data-mock');
            const data = mockData[key];
            if (data && mockStatus) {
                mockMethod.textContent = data.method;
                mockMethod.className = `method-label ${data.method.toLowerCase()}`;
                mockUrl.textContent = data.url;
                mockStatus.textContent = data.status;
                mockStatus.className = `res-status-label ${data.statusClass}`;
                mockReqBody.textContent = data.req;
                mockResBody.textContent = data.res;
            }
        });
    });

    // Touch navigation for mobile/tablets
    let touchStartX = 0;
    let touchEndX = 0;

    document.addEventListener('touchstart', e => {
        touchStartX = e.changedTouches[0].screenX;
    });

    document.addEventListener('touchend', e => {
        touchEndX = e.changedTouches[0].screenX;
        if (touchStartX - touchEndX > 60) nextSlide();
        if (touchEndX - touchStartX > 60) prevSlide();
    });

    // Initial show
    showSlide(1);
});
