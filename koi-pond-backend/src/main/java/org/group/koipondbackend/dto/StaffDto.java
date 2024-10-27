package org.group.koipondbackend.dto;

import java.time.LocalDateTime;

import org.group.koipondbackend.entity.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StaffDto {
    private Long id;
    private String email;
    private String fullName;
    private String phone;
    private String address;
    private Role role;
    private String department;
    private String status;
    private LocalDateTime joinDate;
}
