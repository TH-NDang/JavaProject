package org.group.koipondbackend.dto.auth;

import org.group.koipondbackend.entity.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private Long id;
    private String email;
    private String fullName;
    private Role role;
}
