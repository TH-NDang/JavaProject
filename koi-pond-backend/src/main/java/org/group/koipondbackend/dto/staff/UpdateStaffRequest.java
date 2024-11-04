package org.group.koipondbackend.dto.staff;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UpdateStaffRequest {
    @Email(message = "Email must be valid")
    private String email;

    private String password;

    private String fullName;

    private String phone;

    private String address;

    private String department;
}
