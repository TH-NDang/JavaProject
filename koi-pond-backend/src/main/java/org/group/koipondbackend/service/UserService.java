package org.group.koipondbackend.service;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import org.group.koipondbackend.dto.auth.CreateUserRequest;
import org.group.koipondbackend.dto.user.UpdateStatusRequest;
import org.group.koipondbackend.dto.user.UpdateUserRequest;
import org.group.koipondbackend.dto.user.UserDTO;
import org.group.koipondbackend.entity.User;
import org.group.koipondbackend.entity.enums.Role;
import org.group.koipondbackend.exception.ResourceNotFoundException;
import org.group.koipondbackend.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createCustomerAccount(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.CUSTOMER);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public User createAccount(User user, Role role) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public Page<UserDTO> getAllUsers(PageRequest pageRequest, String search, Role role, String status) {
        Specification<User> spec = Specification.where(null);

        if (StringUtils.hasText(search)) {
            spec = spec.and((root, query, builder) -> builder.or(
                    builder.like(builder.lower(root.get("email")), "%" + search.toLowerCase() + "%"),
                    builder.like(builder.lower(root.get("fullName")), "%" + search.toLowerCase() + "%"),
                    builder.like(builder.lower(root.get("phone")), "%" + search.toLowerCase() + "%")));
        }

        if (role != null) {
            spec = spec.and((root, query, builder) -> builder.equal(root.get("role"), role));
        }

        if (StringUtils.hasText(status)) {
            spec = spec.and((root, query, builder) -> builder.equal(root.get("status"), status));
        }

        return userRepository.findAll(spec, pageRequest)
                .map(this::mapToDTO);
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return mapToDTO(user);
    }

    public UserDTO createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new org.group.koipondbackend.exception.BadRequestException("Email already exists");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .phone(request.getPhone())
                .address(request.getAddress())
                .role(request.getRole())
                .status("ACTIVE")
                .build();

        return mapToDTO(userRepository.save(user));
    }

    public UserDTO updateUser(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (StringUtils.hasText(request.getEmail()) &&
                !request.getEmail().equals(user.getEmail()) &&
                userRepository.existsByEmail(request.getEmail())) {
            throw new org.group.koipondbackend.exception.BadRequestException("Email already exists");
        }

        if (StringUtils.hasText(request.getEmail())) {
            user.setEmail(request.getEmail());
        }
        if (StringUtils.hasText(request.getPassword())) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        if (StringUtils.hasText(request.getFullName())) {
            user.setFullName(request.getFullName());
        }
        if (StringUtils.hasText(request.getPhone())) {
            user.setPhone(request.getPhone());
        }
        if (StringUtils.hasText(request.getAddress())) {
            user.setAddress(request.getAddress());
        }

        return mapToDTO(userRepository.save(user));
    }

    public UserDTO updateUserStatus(Long id, UpdateStatusRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setStatus(request.getStatus());
        return mapToDTO(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found");
        }

        userRepository.deleteById(id);
    }

    // Thêm phương thức để lấy User entity
    public User getUserEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    private UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .phone(user.getPhone())
                .address(user.getAddress())
                .role(user.getRole())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
