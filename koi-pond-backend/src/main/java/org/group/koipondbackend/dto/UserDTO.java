package org.group.koipondbackend.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class UserDTO {
    Long id;
    String username;
    String fullName;
    String password;
    String email;
    String phoneNumber;
}
