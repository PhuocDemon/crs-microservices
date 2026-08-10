package vn.edu.crs.course_service.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import vn.edu.crs.course_service.dto.CourseDTO;
import vn.edu.crs.course_service.service.CourseService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public Page<CourseDTO> search(@RequestParam(required = false) String keyword, Pageable pageable) {
        return courseService.search(keyword, pageable);
    }
}
