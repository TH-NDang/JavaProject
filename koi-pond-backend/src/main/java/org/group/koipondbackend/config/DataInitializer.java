package org.group.koipondbackend.config;

import lombok.RequiredArgsConstructor;

import org.group.koipondbackend.entity.User;
import org.group.koipondbackend.entity.enums.Role;
import org.group.koipondbackend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (!userRepository.existsByEmail("admin@gmail.com")) {
            User adminUser = new User();
            adminUser.setEmail("admin@gmail.com");
            adminUser.setPassword(passwordEncoder.encode("admin123"));
            adminUser.setFullName("System Admin");
            adminUser.setRole(Role.ADMIN);
            adminUser.setPhone("0123456789");
            adminUser.setAddress("Admin Address");
            adminUser.setCreatedAt(LocalDateTime.now());
            adminUser.setUpdatedAt(LocalDateTime.now());

            userRepository.save(adminUser);

            System.out.println("Admin account created successfully!");
            System.out.println("Email: admin@gmail.com");
            System.out.println("Password: admin123");
        }

        // Tài khoản nhân viên mặc định
        if (!userRepository.existsByEmail("staff@gmail.com")) {
            User staffUser = new User();
            staffUser.setEmail("staff@gmail.com");
            staffUser.setPassword(passwordEncoder.encode("staff123"));
            staffUser.setFullName("Staff User");
            staffUser.setRole(Role.STAFF);
            staffUser.setPhone("0987654321");
            staffUser.setAddress("Staff Address");
            staffUser.setCreatedAt(LocalDateTime.now());
            staffUser.setUpdatedAt(LocalDateTime.now());

            userRepository.save(staffUser);

            System.out.println("Staff account created successfully!");
            System.out.println("Email: staff@gmail.com");
            System.out.println("Password: staff123");
        }
    }
}
