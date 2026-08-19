package vn.edu.crs.registrationservice.controller;

import vn.edu.crs.registrationservice.dto.RegistrationRequestDTO;
import vn.edu.crs.registrationservice.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// path: registration-service/src/main/java/vn/edu/crs/registrationservice/controller/RegistrationController.java
// purpose: controller cho dang ky/huy dang ky hoc phan
@RestController
@RequestMapping("/registrations")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    // GET /registrations — lay tat ca (tien ich cho viec kiem tra)
    @GetMapping
    public List<RegistrationRequestDTO> getAll() {
        return registrationService.getAll();
    }

    // GET /registrations/{id}
    @GetMapping("/{id}")
    public RegistrationRequestDTO getById(@PathVariable Long id) {
        return registrationService.getById(id);
    }

    // POST /registrations — dang ky hoc phan
    // Body: { "studentId": "SV001", "courseId": 1 }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegistrationRequestDTO register(@Valid @RequestBody RegistrationRequestDTO dto) {
        return registrationService.register(dto);
    }

    // DELETE /registrations/{id} — huy dang ky
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable Long id) {
        registrationService.cancel(id);
    }
}
