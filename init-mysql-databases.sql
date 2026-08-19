-- ============================================================
-- Script khởi tạo 3 database cho CRS Microservices
-- Chạy file này trong MySQL Workbench trước khi start service
-- ============================================================

-- 1. Tạo database cho auth-service
CREATE DATABASE IF NOT EXISTS auth_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

-- 2. Tạo database cho course-service
CREATE DATABASE IF NOT EXISTS course_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

-- 3. Tạo database cho registration-service
CREATE DATABASE IF NOT EXISTS registration_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

-- Kiểm tra kết quả
SHOW DATABASES;
