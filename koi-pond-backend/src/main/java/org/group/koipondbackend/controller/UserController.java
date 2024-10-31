package org.group.koipondbackend.controller;

import lombok.RequiredArgsConstructor;

import org.group.koipondbackend.dto.auth.CreateUserRequest;
import org.group.koipondbackend.dto.user.UpdateStatusRequest;
import org.group.koipondbackend.dto.user.UpdateUserRequest;
import org.group.koipondbackend.dto.user.UserDTO;
import org.group.koipondbackend.entity.User;
import org.group.koipondbackend.entity.enums.Role;
import org.group.koipondbackend.exception.BadRequestException;
import org.group.koipondbackend.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create-with-role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> createAccountWithRole(@RequestBody User user, @RequestParam Role role) {
        return ResponseEntity.ok(userService.createAccount(user, role));
    }

    // API để lấy danh sách tất cả roles có sẵn
    @GetMapping("/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Role[]> getAllRoles() {
        return ResponseEntity.ok(Role.values());
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<UserDTO>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Role role,
            @RequestParam(required = false) String status) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(userService.getAllUsers(pageRequest, search, role, status));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> updateUserStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateStatusRequest request) {
        return ResponseEntity.ok(userService.updateUserStatus(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        // Lấy thông tin user đang đăng nhập
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();

        // Lấy thông tin user cần xóa
        User userToDelete = userService.getUserEntityById(id);

        // Kiểm tra có phải xóa chính mình không
        if (userToDelete.getEmail().equals(currentUserEmail)) {
            throw new BadRequestException("Cannot delete your own account while logged in");
        }

        // Kiểm tra role ADMIN
        if (userToDelete.getRole() == Role.ADMIN) {
            throw new BadRequestException("Cannot delete admin account");
        }

        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
