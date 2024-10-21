package org.group.koipondbackend.dto;

import lombok.Data;

@Data
public class UserRegisterDTO {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phoneNumber;
}
