package vn.edu.crs.registrationservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

// Test khoi dong Spring context tren H2 in-memory
// Khong can DB rieng, khong can course-service chay that
@SpringBootTest
@ActiveProfiles("test")
class RegistrationServiceApplicationTests {

    @Test
    void contextLoads() {
        // Neu test nay pass = Spring Boot khoi dong thanh cong
        // JPA, H2, Controller, Service, Repository deu duoc wire dung
    }
}
