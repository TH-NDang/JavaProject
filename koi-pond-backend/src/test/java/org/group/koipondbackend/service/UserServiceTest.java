package org.group.koipondbackend.service;

import org.group.koipondbackend.dto.auth.CreateUserRequest;
import org.group.koipondbackend.dto.user.UpdateStatusRequest;
import org.group.koipondbackend.dto.user.UpdateUserRequest;
import org.group.koipondbackend.dto.user.UserDTO;
import org.group.koipondbackend.entity.User;
import org.group.koipondbackend.entity.enums.Role;
import org.group.koipondbackend.exception.ResourceNotFoundException;
import org.group.koipondbackend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCustomerAccount() {
        User user = new User();
        user.setPassword("password");

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createCustomerAccount(user);

        assertEquals("encodedPassword", createdUser.getPassword());
        assertEquals(Role.CUSTOMER, createdUser.getRole());
        assertNotNull(createdUser.getCreatedAt());
        assertNotNull(createdUser.getUpdatedAt());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testCreateAccount() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createAccount(user, Role.ADMIN);

        assertEquals("encodedPassword", createdUser.getPassword());
        assertEquals(Role.ADMIN, createdUser.getRole());
        assertNotNull(createdUser.getCreatedAt());
        assertNotNull(createdUser.getUpdatedAt());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testGetAllUsers() {
        // Setup test data
        PageRequest pageRequest = PageRequest.of(0, 10);
        User user = new User();
        user.setId(1L);
        user.setFullName("Test User");
        user.setEmail("test@example.com");
        user.setRole(Role.CUSTOMER);
        user.setStatus("ACTIVE");

        Page<User> userPage = new PageImpl<>(Collections.singletonList(user));
        // Setup mock with specific search criteria
        when(userRepository.findAll(
                any(Specification.class),
                eq(pageRequest))).thenReturn(userPage);

        // Execute
        Page<UserDTO> result = userService.getAllUsers(pageRequest, "search", Role.CUSTOMER, "ACTIVE");

        // Verify
        assertEquals(1, result.getTotalElements());
        assertFalse(result.getContent().isEmpty());
        UserDTO dto = result.getContent().get(0);
        assertEquals(user.getId(), dto.getId());
        assertEquals(user.getFullName(), dto.getFullName());
        assertEquals(user.getEmail(), dto.getEmail());
        assertEquals(user.getRole(), dto.getRole());
        assertEquals(user.getStatus(), dto.getStatus());

        verify(userRepository, times(1)).findAll(any(Specification.class), eq(pageRequest));
    }

    @Test
    void testGetUserById() {
        User user = new User();
        user.setId(1L);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        UserDTO userDTO = userService.getUserById(1L);

        assertEquals(1L, userDTO.getId());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateUser() {
        CreateUserRequest request = new CreateUserRequest();
        request.setEmail("test@example.com");
        request.setPassword("password");

        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(new User());

        UserDTO userDTO = userService.createUser(request);

        assertNotNull(userDTO);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testUpdateUser() {
        UpdateUserRequest request = new UpdateUserRequest();
        request.setEmail("newemail@example.com");

        User user = new User();
        user.setId(1L);
        user.setEmail("oldemail@example.com");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO userDTO = userService.updateUser(1L, request);

        assertEquals("newemail@example.com", userDTO.getEmail());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testUpdateUserStatus() {
        UpdateStatusRequest request = new UpdateStatusRequest();
        request.setStatus("INACTIVE");

        User user = new User();
        user.setId(1L);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO userDTO = userService.updateUserStatus(1L, request);

        assertEquals("INACTIVE", userDTO.getStatus());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testDeleteUser() {
        when(userRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(userRepository).deleteById(anyLong());

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetUserEntityById() {
        User user = new User();
        user.setId(1L);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        User result = userService.getUserEntityById(1L);

        assertEquals(1L, result.getId());
        verify(userRepository, times(1)).findById(1L);
    }
}
