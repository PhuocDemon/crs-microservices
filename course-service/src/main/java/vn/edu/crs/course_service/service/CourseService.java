package vn.edu.crs.course_service.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.crs.course_service.dto.CourseDTO;
import vn.edu.crs.course_service.entity.Course;
import vn.edu.crs.course_service.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    
    // Giả sử có sẵn các phương thức toDTO, toEntity từ Buổi 2
    private <CourseDTO> CourseDTO toDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setTenMonHoc(course.getTenMonHoc());
        dto.setSoChoToiDa(course.getSoChoToiDa());
        dto.setSoChoConLai(course.getSoChoConLai());
        return dto;
    }

    public Page<CourseDTO> search(String keyword, Pageable pageable) {
        Page<Course> page = (keyword == null || keyword.isBlank())
            ? courseRepository.findAll(pageable)
            : courseRepository.findByTenMonHocContainingIgnoreCase(keyword, pageable);
        return page.map(this::toDTO);
    }
    
    @Transactional
    public CourseDTO reserveSeat(Long courseId) {
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new NoSuchElementException("Khong tim thay mon hoc id = " + courseId));
        if (course.getSoChoConLai() <= 0) {
            throw new IllegalStateException("Mon hoc da het cho, khong the dang ky");
        }
        course.setSoChoConLai(course.getSoChoConLai() - 1);
        return toDTO(courseRepository.save(course));
    }

    @Transactional
    public CourseDTO releaseSeat(Long courseId) {
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new NoSuchElementException("Khong tim thay mon hoc id = " + courseId));
        if (course.getSoChoConLai() < course.getSoChoToiDa()) {
            course.setSoChoConLai(course.getSoChoConLai() + 1);
        }
        return toDTO(courseRepository.save(course));
    }
}
