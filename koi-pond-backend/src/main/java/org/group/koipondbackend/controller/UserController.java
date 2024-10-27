package org.group.koipondbackend.controller;

import lombok.RequiredArgsConstructor;

import org.group.koipondbackend.entity.User;
import org.group.koipondbackend.entity.enums.Role;
import org.group.koipondbackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> createAccount(@RequestBody User user, @RequestParam Role role) {
        return ResponseEntity.ok(userService.createAccount(user, role));
    }

    // API để lấy danh sách tất cả roles có sẵn
    @GetMapping("/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Role[]> getAllRoles() {
        return ResponseEntity.ok(Role.values());
    }
}
