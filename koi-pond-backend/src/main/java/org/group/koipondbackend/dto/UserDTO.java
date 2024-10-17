package org.group.koipondbackend.dto;

import lombok.Data;

@Data
public abstract class UserDTO {
    private Long id;
    private String username;
    private String fullName;
    private String password;
    private String email;
    private String phoneNumber;

    public abstract String getName();

    public abstract String getPhone();
}
