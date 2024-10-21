package org.group.koipondbackend.repository;

import org.group.koipondbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository<T extends User> extends JpaRepository<T, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);

    // Thêm phương thức tìm kiếm theo email
    Optional<User> findByEmail(String email);

    // Kiểm tra xem email có tồn tại hay không
    boolean existsByEmail(String email);

    // Thêm phương thức tìm kiếm theo số điện thoại
    Optional<User> findByPhoneNumber(String phoneNumber);

    // Kiểm tra xem số điện thoại có tồn tại hay không
    boolean existsByPhoneNumber(String phoneNumber);

    // Thêm phương thức tìm kiếm theo tên đầy đủ
    Optional<User> findByFullName(String fullName);

    // Thêm phương thức tìm kiếm theo vai trò người dùng (role)
    Optional<User> findByRole(String role);
}
