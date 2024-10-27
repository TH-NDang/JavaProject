package org.group.koipondbackend.dto;

import org.mindrot.jbcrypt.BCrypt;

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

    // Phương thức để mã hóa password
    public void setPassword(String password) {
        this.password = hashPassword(password);
    }

    // Phương thức mã hóa
    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Phương thức để kiểm tra password
    public boolean checkPassword(String password) {
        return BCrypt.checkpw(password, this.password);
    }
}
