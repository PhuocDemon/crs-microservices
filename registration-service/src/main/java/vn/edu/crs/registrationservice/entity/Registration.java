package vn.edu.crs.registrationservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

// Thuc the dang ky hoc phan
@Entity
@Table(name = "registrations")
@Getter
@Setter
@NoArgsConstructor
public class Registration {

    // Hang so trang thai — tranh loi chinh ta khi dung String tu do
    public static final String DA_DANG_KY = "DA_DANG_KY";
    public static final String DA_HUY     = "DA_HUY";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Ma sinh vien (chi luu ID, khong join vi day la microservice rieng)
    @Column(nullable = false)
    private String studentId;

    // ID mon hoc ben course-service (khong co FK that su vi khac DB)
    @Column(nullable = false)
    private Long courseId;

    @Column(nullable = false)
    private String trangThai = DA_DANG_KY;

    @Column(nullable = false)
    private LocalDateTime ngayDangKy = LocalDateTime.now();
}
