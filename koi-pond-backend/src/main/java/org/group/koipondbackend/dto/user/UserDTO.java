package org.group.koipondbackend.dto.user;

import java.time.LocalDateTime;

import org.group.koipondbackend.entity.enums.Role;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    Long id;
    String email;
    String fullName;
    String phone;
    String address;
    Role role;
    String status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
