package vn.edu.crs.registrationservice.repository;

import vn.edu.crs.registrationservice.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    // Tim tat ca dang ky cua mot sinh vien
    List<Registration> findByStudentId(String studentId);

    // Kiem tra sinh vien da dang ky mon nay chua, bo qua ban ghi da huy
    boolean existsByStudentIdAndCourseIdAndTrangThaiNot(
            String studentId, Long courseId, String trangThai);
}
