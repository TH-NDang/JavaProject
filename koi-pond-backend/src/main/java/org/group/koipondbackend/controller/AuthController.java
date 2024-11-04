package org.group.koipondbackend.controller;

import lombok.RequiredArgsConstructor;

import org.group.koipondbackend.dto.auth.LoginRequest;
import org.group.koipondbackend.dto.auth.LoginResponse;
import org.group.koipondbackend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
