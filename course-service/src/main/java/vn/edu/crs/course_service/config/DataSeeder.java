package vn.edu.crs.course_service.config;

import vn.edu.crs.course_service.entity.Course;
import vn.edu.crs.course_service.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * DataSeeder: tự động tạo dữ liệu môn học mẫu khi khởi động lần đầu.
 * Dùng kiểm tra trước khi insert để không bị duplicate khi restart.
 */
@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final CourseRepository courseRepository;

    @Override
    public void run(String... args) {
        if (courseRepository.count() == 0) {
            courseRepository.save(buildCourse("Lập Trình Java", 3, 30));
            courseRepository.save(buildCourse("Cơ Sở Dữ Liệu", 3, 25));
            courseRepository.save(buildCourse("Mạng Máy Tính", 3, 30));
            courseRepository.save(buildCourse("Toán Rời Rạc", 2, 40));
            courseRepository.save(buildCourse("Trí Tuệ Nhân Tạo", 3, 20));
            System.out.println("[DataSeeder] Đã khởi tạo 5 môn học mẫu vào course_db.");
        } else {
            System.out.println("[DataSeeder] Dữ liệu môn học đã tồn tại, bỏ qua khởi tạo.");
        }
    }

    private Course buildCourse(String tenMonHoc, int soTinChi, int soChoToiDa) {
        Course c = new Course();
        c.setTenMonHoc(tenMonHoc);
        c.setSoTinChi(soTinChi);
        c.setSoChoToiDa(soChoToiDa);
        c.setSoChoConLai(soChoToiDa); // ban đầu chỗ còn lại = chỗ tối đa
        return c;
    }
}
