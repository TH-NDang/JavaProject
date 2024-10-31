package org.group.koipondbackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.group.koipondbackend.dto.auth.LoginRequest;
import org.group.koipondbackend.dto.auth.LoginResponse;
import org.group.koipondbackend.dto.auth.UserInfo;
import org.group.koipondbackend.entity.User;
import org.group.koipondbackend.repository.UserRepository;
import org.group.koipondbackend.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;

    public LoginResponse login(LoginRequest request) {
        log.debug("Attempting login for user: {}", request.getEmail());

        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        // Get user from database
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found: " + request.getEmail()));

        // Generate token
        String jwt = tokenProvider.generateToken(user);

        // Create user info
        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setEmail(user.getEmail());
        userInfo.setFullName(user.getFullName());
        userInfo.setRole(user.getRole());

        log.debug("Login successful for user: {}", request.getEmail());

        return new LoginResponse(jwt, userInfo);
    }
}
