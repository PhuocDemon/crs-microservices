// path: auth-service/src/main/java/vn/edu/crs/authservice/dto/LoginRequestDTO.java
// purpose: DTO nhan username/password khi dang nhap

package vn.edu.crs.authservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {
    @NotBlank(message = "Username khong duoc de trong")
    private String username;

    @NotBlank(message = "Password khong duoc de trong")
    private String password;
}
