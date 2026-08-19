package vn.edu.crs.registrationservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// DTO dung cho request dang ky hoc phan (POST /registrations)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequestDTO {

    private Long id;

    @NotBlank(message = "studentId khong duoc de trong")
    private String studentId;

    @NotNull(message = "courseId khong duoc de trong")
    private Long courseId;

    // Chi co trong response, khong can gui len khi POST
    private String trangThai;

    private LocalDateTime ngayDangKy;
}
