package vn.edu.crs.registrationservice.service;

import vn.edu.crs.registrationservice.client.CourseClient;
import vn.edu.crs.registrationservice.dto.RegistrationRequestDTO;
import vn.edu.crs.registrationservice.entity.Registration;
import vn.edu.crs.registrationservice.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final CourseClient courseClient;

    // Lay tat ca dang ky
    public List<RegistrationRequestDTO> getAll() {
        return registrationRepository.findAll()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    // Lay dang ky theo ID
    public RegistrationRequestDTO getById(Long id) {
        Registration reg = registrationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Khong tim thay dang ky id=" + id));
        return toDTO(reg);
    }

    /**
     * Dang ky hoc phan — theo dung thu tu:
     * B1: Kiem tra sinh vien da dang ky mon nay chua (tranh trung)
     * B2: Goi course-service de tru cho TRUOC.
     *     Neu buoc nay nem exception, ham se dung lai ngay, KHONG luu Registration.
     * B3: Chi luu Registration SAU KHI course-service xac nhan tru cho thanh cong.
     *
     * LUU Y (thao luan o muc D ben duoi): neu dong save() nay that bai
     * (vi du mat ket noi DB trong luc nay), cho da bi tru o course-service nhung khong co
     * ban ghi Registration nao duoc tao.
     * Day la gioi han biet cua kien truc don gian hoa nay.
     */
    @Transactional
    public RegistrationRequestDTO register(RegistrationRequestDTO dto) {
        // Buoc 1: Kiem tra dang ky trung
        boolean existed = registrationRepository.existsByStudentIdAndCourseIdAndTrangThaiNot(
                dto.getStudentId(), dto.getCourseId(), Registration.DA_HUY);
        if (existed) {
            throw new IllegalStateException(
                    "Sinh vien " + dto.getStudentId() + " da dang ky mon hoc nay roi");
        }

        // Buoc 2: Goi sang course-service de tru cho TRUOC.
        // Neu buoc nay nem exception, ham se dung lai ngay, KHONG luu Registration.
        courseClient.reserveSeat(dto.getCourseId());

        // Buoc 3: Chi luu Registration SAU KHI course-service xac nhan thanh cong.
        Registration registration = new Registration();
        registration.setStudentId(dto.getStudentId());
        registration.setCourseId(dto.getCourseId());
        registration.setTrangThai(Registration.DA_DANG_KY);
        registration.setNgayDangKy(LocalDateTime.now());

        return toDTO(registrationRepository.save(registration));
    }

    /**
     * Huy dang ky — theo dung thu tu:
     * B1: Tim ban ghi, kiem tra chua bi huy
     * B2: Goi release-seat sang course-service TRUOC khi doi trang thai.
     * B3: Doi trangThai = DA_HUY va luu lai.
     */
    @Transactional
    public void cancel(Long registrationId) {
        Registration registration = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new NoSuchElementException(
                        "Khong tim thay dang ky id = " + registrationId));

        if (Registration.DA_HUY.equals(registration.getTrangThai())) {
            throw new IllegalStateException("Dang ky nay da duoc huy truoc do roi");
        }

        // Goi sang course-service de hoan tra cho TRUOC khi doi trang thai
        courseClient.releaseSeat(registration.getCourseId());

        registration.setTrangThai(Registration.DA_HUY);
        registrationRepository.save(registration);
    }

    // Chuyen entity → DTO
    private RegistrationRequestDTO toDTO(Registration reg) {
        return new RegistrationRequestDTO(
                reg.getId(),
                reg.getStudentId(),
                reg.getCourseId(),
                reg.getTrangThai(),
                reg.getNgayDangKy()
        );
    }
}
