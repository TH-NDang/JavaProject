package org.group.koipondbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(name = "full_name")
    private String fullName;
    private String password;

    @Column(unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    // Thêm thuộc tính role để phân quyền người dùng
    @Column(name = "role")
    private String role; // Vai trò của người dùng (USER, ADMIN, etc.)

    // Thêm thuộc tính cho trạng thái tài khoản
    @Column(name = "is_active")
    private boolean isActive; // Trạng thái tài khoản (đang hoạt động hay không)

    // Phương thức kiểm tra vai trò của người dùng
    public boolean hasRole(String role) {
        return this.role != null && this.role.equalsIgnoreCase(role);
    }

    // Phương thức để kích hoạt hoặc vô hiệu hóa tài khoản
    public void setActive(boolean active) {
        isActive = active;
    }

    // Getter cho isActive
    public boolean isActive() {
        return isActive;
    }
}
