# Project Structure: 

## Directory Structure

- 📁 .vscode/
  - 📄 settings.json
- 📁 src/
  - 📁 main/
    - 📁 java/
      - 📁 org/
        - 📁 group/
          - 📁 koipondbackend/
            - 📄 KoiPondBackendApplication.java
            - 📁 config/
              - 📄 DataInitializer.java
              - 📄 JwtProperties.java
              - 📄 SecurityConfig.java
            - 📁 controller/
              - 📄 AuthController.java
              - 📄 FeedbackController.java
              - 📄 ProjectController.java
              - 📄 ServiceController.java
              - 📄 StaffController.java
              - 📄 UserController.java
            - 📁 dto/
              - 📄 ConstructionStaffDTO.java
              - 📄 ConstructionStaffDto.java
              - 📄 ConsultingStaffDTO.java
              - 📄 ConsultingStaffDto.java
              - 📄 CreateConstructionStaffRequest.java
              - 📄 CreateConsultingStaffRequest.java
              - 📄 CreateDesignStaffRequest.java
              - 📄 CreateStaffRequest.java
              - 📄 DesignStaffDTO.java
              - 📄 DesignStaffDto.java
              - 📄 FeedbackDTO.java
              - 📄 OrderDTO.java
              - 📄 ProjectDTO.java
              - 📄 ServiceDTO.java
              - 📄 StaffDTO.java
              - 📄 StaffDto.java
              - 📄 UpdateStaffRequest.java
              - 📄 UpdateStaffStatusRequest.java
              - 📄 UserDTO.java
              - 📁 auth/
                - 📄 CreateUserRequest.java
                - 📄 LoginRequest.java
                - 📄 LoginResponse.java
                - 📄 UserInfo.java
            - 📁 entity/
              - 📄 ConstructionStaff.java
              - 📄 ConsultingStaff.java
              - 📄 DesignStaff.java
              - 📄 Feedback.java
              - 📄 Order.java
              - 📄 Project.java
              - 📄 Service.java
              - 📄 Staff.java
              - 📄 User.java
              - 📁 enums/
                - 📄 Role.java
            - 📁 exception/
              - 📄 ErrorResponse.java
              - 📄 GlobalExceptionHandler.java
              - 📄 ResourceNotFoundException.java
              - 📄 UnauthorizedException.java
            - 📁 mapper/
              - 📄 ConstructionStaffMapper.java
              - 📄 ConsultingStaffMapper.java
              - 📄 DesignStaffMapper.java
              - 📄 FeedbackMapper.java
              - 📄 GenericMapper.java
              - 📄 OrderMapper.java
              - 📄 ProjectMapper.java
              - 📄 ServiceMapper.java
            - 📁 repository/
              - 📄 ConstructionStaffRepository.java
              - 📄 ConsultingStaffRepository.java
              - 📄 DesignStaffRepository.java
              - 📄 FeedbackRepository.java
              - 📄 OrderRepository.java
              - 📄 ProjectRepository.java
              - 📄 ServiceRepository.java
              - 📄 StaffRepository.java
              - 📄 UserRepository.java
            - 📁 security/
              - 📄 CustomUserDetailsService.java
              - 📄 JwtAuthenticationFilter.java
              - 📄 JwtTokenProvider.java
            - 📁 service/
              - 📄 AuthService.java
              - 📄 UserService.java
              - 📁 impl/
                - 📄 AbstractUserService.java
                - 📄 FeedbackService.java
                - 📄 OrderService.java
                - 📄 ProjectService.java
                - 📄 ServiceService.java
              - 📁 interfaces/
                - 📄 UserService.java
              - 📁 staff/
                - 📄 StaffService.java
    - 📁 resources/
      - 📄 application.yml
      - 📁 static/
        - 📄 index.html

## File Contents

### 📄 .vscode/settings.json

```
{
  "java.configuration.updateBuildConfiguration": "interactive",
  "java.compile.nullAnalysis.mode": "disabled"
}

```

### 📄 src/main/resources/application.yml

```
spring:
  application:
    name: koi-pond-backend

  datasource:
    url: jdbc:sqlserver://localhost:1433;databaseName=master;encrypt=true;trustServerCertificate=true
    username: sa
    password: Password@sa
    driverClassName: com.microsoft.sqlserver.jdbc.SQLServerDriver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.SQLServerDialect
        format_sql: true

  flyway:
    enabled: true
    baseline-on-migrate: true
    locations: classpath:db/migration

app:
  jwt:
    secret: 5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437
    expiration: 86400000  # 24 hours


logging:
  level:
    org.springframework.security: DEBUG
    org.springframework.web: DEBUG
    org.hibernate: INFO
    com.example.koipond: DEBUG

```

### 📄 src/main/resources/static/index.html

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Successfully Deployed</title>
</head>
<body>
    <h1>Successfully Deployed</h1>
    <p>Spring Boot Application is up and running</p>
</body>
</html>
```

### 📄 src/main/java/org/group/koipondbackend/KoiPondBackendApplication.java

```
package org.group.koipondbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KoiPondBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(KoiPondBackendApplication.class, args);
	}
	@GetMapping("/hello")
	public String hello() {
		return "Hello, World!";
	}
}

```

### 📄 src/main/java/org/group/koipondbackend/dto/ProjectDTO.java

```
package org.group.koipondbackend.dto;

import lombok.Data;

@Data
public class ProjectDTO {
    private Long id;
    private String name;
    private String description;
    // Các thuộc tính khác của dự án
}
```

### 📄 src/main/java/org/group/koipondbackend/dto/ConstructionStaffDTO.java

```
package org.group.koipondbackend.dto;

import jakarta.persistence.DiscriminatorValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("CONSTRUCTION")
@Data
@NoArgsConstructor
public class ConstructionStaffDTO extends StaffDTO {
    private String constructionSkills;
}

```

### 📄 src/main/java/org/group/koipondbackend/dto/UserDTO.java

```
package org.group.koipondbackend.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
    Long id;
    String username;
    String fullName;
    String password;
    String email;
    String phoneNumber;
}

```

### 📄 src/main/java/org/group/koipondbackend/dto/ServiceDTO.java

```
package org.group.koipondbackend.dto;

import lombok.Data;

@Data
public class ServiceDTO {
    private Long id;              // ID của dịch vụ
    private String name;          // Tên dịch vụ
    private String description;    // Mô tả dịch vụ
}
```

### 📄 src/main/java/org/group/koipondbackend/dto/StaffDto.java

```
package org.group.koipondbackend.dto;

import java.time.LocalDateTime;

import org.group.koipondbackend.entity.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StaffDto {
    private Long id;
    private String email;
    private String fullName;
    private String phone;
    private String address;
    private Role role;
    private String department;
    private String status;
    private LocalDateTime joinDate;
}

```

### 📄 src/main/java/org/group/koipondbackend/dto/UpdateStaffStatusRequest.java

```
package org.group.koipondbackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateStaffStatusRequest {
    @NotBlank(message = "Status is required")
    private String status;

    private String reason;
}

```

### 📄 src/main/java/org/group/koipondbackend/dto/CreateDesignStaffRequest.java

```
package org.group.koipondbackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateDesignStaffRequest extends CreateStaffRequest {
    @NotBlank(message = "Design specialization is required")
    private String designSpecialization;

    private String softwareSkills;
    private String portfolioUrl;
}

```

### 📄 src/main/java/org/group/koipondbackend/dto/OrderDTO.java

```
package org.group.koipondbackend.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private Long id;              // ID của đơn hàng
    private Long projectId;      // ID của dự án liên quan
    private String orderDetails;  // Chi tiết đơn hàng
    private Double amount;        // Số tiền của đơn hàng
    // Thêm các thuộc tính khác nếu cần
}
```

### 📄 src/main/java/org/group/koipondbackend/dto/DesignStaffDTO.java

```
package org.group.koipondbackend.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class DesignStaffDTO extends StaffDTO {
    private String designSoftware;
}

```

### 📄 src/main/java/org/group/koipondbackend/dto/StaffDTO.java

```
package org.group.koipondbackend.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StaffDTO extends UserDTO {
    private String employeeId;
    private String department;
}

```

### 📄 src/main/java/org/group/koipondbackend/dto/ConsultingStaffDto.java

```
package org.group.koipondbackend.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ConsultingStaffDto extends StaffDto {
    private String specialization;
    private Integer experienceYears;
    private Integer customersHandled;
}

```

### 📄 src/main/java/org/group/koipondbackend/dto/ConstructionStaffDto.java

```
package org.group.koipondbackend.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class ConstructionStaffDto extends StaffDto {
    private String certification;
    private String equipmentExpertise;
    private LocalDate safetyTrainingExpiry;
}

```

### 📄 src/main/java/org/group/koipondbackend/dto/DesignStaffDto.java

```
package org.group.koipondbackend.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DesignStaffDto extends StaffDto {
    private String designSpecialization;
    private String softwareSkills;
    private String portfolioUrl;
}

```

### 📄 src/main/java/org/group/koipondbackend/dto/CreateConsultingStaffRequest.java

```
package org.group.koipondbackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateConsultingStaffRequest extends CreateStaffRequest {
    @NotBlank(message = "Specialization is required")
    private String specialization;

    private Integer experienceYears;
}

```

### 📄 src/main/java/org/group/koipondbackend/dto/FeedbackDTO.java

```
package org.group.koipondbackend.dto;

import java.time.LocalDateTime;

public class FeedbackDTO {

    private Long id;
    private Long customerId;
    private String content;
    private int rating;
    private LocalDateTime createdAt;

    // Getters and setters
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
```

### 📄 src/main/java/org/group/koipondbackend/dto/ConsultingStaffDTO.java

```
package org.group.koipondbackend.dto;

import jakarta.persistence.DiscriminatorValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("CONSULTING")
@Data
@NoArgsConstructor
public class ConsultingStaffDTO extends StaffDTO {
    private String specialization;
}

```

### 📄 src/main/java/org/group/koipondbackend/dto/CreateConstructionStaffRequest.java

```
package org.group.koipondbackend.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateConstructionStaffRequest extends CreateStaffRequest {
    @NotBlank(message = "Certification is required")
    private String certification;

    private String equipmentExpertise;
    private LocalDate safetyTrainingExpiry;
}

```

### 📄 src/main/java/org/group/koipondbackend/dto/CreateStaffRequest.java

```
package org.group.koipondbackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class CreateStaffRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Phone is required")
    private String phone;

    private String address;

    @NotBlank(message = "Department is required")
    private String department;

    @Builder.Default
    private String status = "ACTIVE";
}

```

### 📄 src/main/java/org/group/koipondbackend/dto/UpdateStaffRequest.java

```
package org.group.koipondbackend.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UpdateStaffRequest {
    @Email(message = "Email must be valid")
    private String email;

    private String password;

    private String fullName;

    private String phone;

    private String address;

    private String department;
}

```

### 📄 src/main/java/org/group/koipondbackend/config/DataInitializer.java

```
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

```

### 📄 src/main/java/org/group/koipondbackend/config/JwtProperties.java

```
package org.group.koipondbackend.config;

import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Component
@Getter
public class JwtProperties {

    private final SecretKey secretKey;
    private final long expiration;

    public JwtProperties(
            @Value("${app.jwt.secret}") String secret,
            @Value("${app.jwt.expiration}") long expiration) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expiration = expiration;
    }
}

```

### 📄 src/main/java/org/group/koipondbackend/config/SecurityConfig.java

```
package org.group.koipondbackend.config;

import lombok.RequiredArgsConstructor;
import org.group.koipondbackend.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/public/**").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

```

### 📄 src/main/java/org/group/koipondbackend/exception/ErrorResponse.java

```
package org.group.koipondbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String code;
    private String message;
    private String details;
}

```

### 📄 src/main/java/org/group/koipondbackend/exception/GlobalExceptionHandler.java

```
package org.group.koipondbackend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
        log.error("Unexpected error occurred", ex);
        ErrorResponse errorResponse = new ErrorResponse(
                "INTERNAL_SERVER_ERROR",
                ex.getMessage(),
                ex.getClass().getName());
        return ResponseEntity.internalServerError().body(errorResponse);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException ex) {
        log.error("Authentication error", ex);
        ErrorResponse errorResponse = new ErrorResponse(
                "AUTHENTICATION_ERROR",
                ex.getMessage(),
                ex.getClass().getName());
        return ResponseEntity.status(401).body(errorResponse);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException ex) {
        log.error("Bad credentials", ex);
        ErrorResponse errorResponse = new ErrorResponse(
                "INVALID_CREDENTIALS",
                "Invalid email or password",
                ex.getClass().getName());
        return ResponseEntity.status(401).body(errorResponse);
    }
}

```

### 📄 src/main/java/org/group/koipondbackend/exception/ResourceNotFoundException.java

```
package org.group.koipondbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

```

### 📄 src/main/java/org/group/koipondbackend/exception/UnauthorizedException.java

```
package org.group.koipondbackend.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}

```

### 📄 src/main/java/org/group/koipondbackend/service/UserService.java

```
package org.group.koipondbackend.service;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import org.group.koipondbackend.entity.User;
import org.group.koipondbackend.entity.enums.Role;
import org.group.koipondbackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository<User> userRepository;
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
}

```

### 📄 src/main/java/org/group/koipondbackend/service/AuthService.java

```
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
    private final UserRepository<User> userRepository;

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

```

### 📄 src/main/java/org/group/koipondbackend/repository/ConsultingStaffRepository.java

```
package org.group.koipondbackend.repository;

import org.group.koipondbackend.entity.ConsultingStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultingStaffRepository extends JpaRepository<ConsultingStaff, Long> {
    List<ConsultingStaff> findBySpecialization(String specialization);

    List<ConsultingStaff> findByExperienceYearsGreaterThan(Integer years);
}

```

### 📄 src/main/java/org/group/koipondbackend/repository/FeedbackRepository.java

```
package org.group.koipondbackend.repository;

import org.group.koipondbackend.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByCustomerId(Long customerId);
}
```

### 📄 src/main/java/org/group/koipondbackend/repository/UserRepository.java

```
package org.group.koipondbackend.repository;

import java.util.Optional;

import org.group.koipondbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository<T extends User> extends JpaRepository<T, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}

```

### 📄 src/main/java/org/group/koipondbackend/repository/OrderRepository.java

```
package org.group.koipondbackend.repository;

import org.group.koipondbackend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByProjectId(Long projectId); // Tìm kiếm đơn hàng theo projectId
}
```

### 📄 src/main/java/org/group/koipondbackend/repository/DesignStaffRepository.java

```
package org.group.koipondbackend.repository;

import org.group.koipondbackend.entity.DesignStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DesignStaffRepository extends JpaRepository<DesignStaff, Long> {
    List<DesignStaff> findByDesignSpecialization(String specialization);
}

```

### 📄 src/main/java/org/group/koipondbackend/repository/ProjectRepository.java

```
package org.group.koipondbackend.repository;

import org.group.koipondbackend.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // Có thể thêm các phương thức truy vấn tùy chỉnh tại đây
}
```

### 📄 src/main/java/org/group/koipondbackend/repository/ServiceRepository.java

```
package org.group.koipondbackend.repository;

import org.group.koipondbackend.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    // Có thể thêm các phương thức truy vấn tùy chỉnh tại đây
}
```

### 📄 src/main/java/org/group/koipondbackend/repository/StaffRepository.java

```
package org.group.koipondbackend.repository;

import java.util.List;

import org.group.koipondbackend.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    List<Staff> findByDepartment(String department);

    List<Staff> findByStatus(String status);

    List<Staff> findByDepartmentAndStatus(String department, String status);

    @Query("SELECT s FROM Staff s WHERE " +
            "(LOWER(s.fullName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(s.email) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND (:department IS NULL OR s.department = :department) " +
            "AND (:status IS NULL OR s.status = :status)")
    List<Staff> search(
            @Param("keyword") String keyword,
            @Param("department") String department,
            @Param("status") String status);
}

```

### 📄 src/main/java/org/group/koipondbackend/repository/ConstructionStaffRepository.java

```
package org.group.koipondbackend.repository;

import org.group.koipondbackend.entity.ConstructionStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDate;

@Repository
public interface ConstructionStaffRepository extends JpaRepository<ConstructionStaff, Long> {
    List<ConstructionStaff> findByCertification(String certification);

    List<ConstructionStaff> findBySafetyTrainingExpiryBefore(LocalDate date);
}

```

### 📄 src/main/java/org/group/koipondbackend/security/JwtAuthenticationFilter.java

```
package org.group.koipondbackend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                Long userId = tokenProvider.getUserIdFromToken(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(userId.toString());

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}

```

### 📄 src/main/java/org/group/koipondbackend/security/JwtTokenProvider.java

```
package org.group.koipondbackend.security;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.group.koipondbackend.config.JwtProperties;
import org.group.koipondbackend.entity.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    public String generateToken(User user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtProperties.getExpiration());

        return Jwts.builder()
                .setSubject(Long.toString(user.getId()))
                .claim("email", user.getEmail())
                .claim("role", user.getRole().name())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(jwtProperties.getSecretKey())
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtProperties.getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}

```

### 📄 src/main/java/org/group/koipondbackend/security/CustomUserDetailsService.java

```
package org.group.koipondbackend.security;

import lombok.RequiredArgsConstructor;
import org.group.koipondbackend.entity.User;
import org.group.koipondbackend.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

        private final UserRepository<User> userRepository;

        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                User user = userRepository.findByEmail(email)
                                .orElseThrow(() -> new UsernameNotFoundException(
                                                "User not found with email: " + email));

                return org.springframework.security.core.userdetails.User
                                .withUsername(user.getEmail())
                                .password(user.getPassword())
                                .authorities(Collections.singletonList(
                                                new SimpleGrantedAuthority("ROLE_" + user.getRole().name())))
                                .accountExpired(false)
                                .accountLocked(false)
                                .credentialsExpired(false)
                                .disabled(false)
                                .build();
        }
}

```

### 📄 src/main/java/org/group/koipondbackend/entity/Feedback.java

```
package org.group.koipondbackend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;
    private String content;
    private int rating;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
```

### 📄 src/main/java/org/group/koipondbackend/entity/ConsultingStaff.java

```
package org.group.koipondbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "consulting_staff")
@PrimaryKeyJoinColumn(name = "staff_id")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class ConsultingStaff extends Staff {
    @Column(name = "specialization")
    private String specialization;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(name = "customers_handled")
    private Integer customersHandled;
}

```

### 📄 src/main/java/org/group/koipondbackend/entity/Staff.java

```
package org.group.koipondbackend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "staff")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Staff extends User {
    @Column(name = "department")
    private String department;

    @Column(name = "join_date")
    private LocalDateTime joinDate;

    @Column(name = "status")
    private String status; // ACTIVE, INACTIVE, ON_LEAVE, etc.
}

```

### 📄 src/main/java/org/group/koipondbackend/entity/Order.java

```
package org.group.koipondbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long projectId;      // ID của dự án liên quan
    private String orderDetails;  // Chi tiết đơn hàng
    private Double amount;        // Số tiền của đơn hàng

    // Thêm các thuộc tính khác nếu cần
}
```

### 📄 src/main/java/org/group/koipondbackend/entity/ConstructionStaff.java

```
package org.group.koipondbackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;

@Entity
@Table(name = "construction_staff")
@PrimaryKeyJoinColumn(name = "staff_id")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class ConstructionStaff extends Staff {

    @Column(name = "certification")
    private String certification;

    @Column(name = "equipment_expertise")
    private String equipmentExpertise;

    @Column(name = "safety_training")
    private LocalDate safetyTrainingExpiry;
}

```

### 📄 src/main/java/org/group/koipondbackend/entity/Project.java

```
package org.group.koipondbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "projects")
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    // Các thuộc tính khác của dự án
}
```

### 📄 src/main/java/org/group/koipondbackend/entity/User.java

```
package org.group.koipondbackend.entity;

import jakarta.persistence.*;
import lombok.*;

import lombok.experimental.SuperBuilder;

import org.group.koipondbackend.entity.enums.Role;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column
    private String phone;

    @Column
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

```

### 📄 src/main/java/org/group/koipondbackend/entity/Service.java

```
package org.group.koipondbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "services")
@Data
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;          // Tên dịch vụ
    private String description;    // Mô tả dịch vụ
}
```

### 📄 src/main/java/org/group/koipondbackend/entity/DesignStaff.java

```
package org.group.koipondbackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "design_staff")
@PrimaryKeyJoinColumn(name = "staff_id")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@DiscriminatorValue("DESIGN")
public class DesignStaff extends Staff {

    @Column(name = "design_specialization")
    private String designSpecialization;

    @Column(name = "software_skills")
    private String softwareSkills;

    @Column(name = "portfolio_url")
    private String portfolioUrl;
}

```

### 📄 src/main/java/org/group/koipondbackend/mapper/ServiceMapper.java

```
package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.ServiceDTO;
import org.group.koipondbackend.entity.Service;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceMapper {

    public ServiceDTO toDto(Service entity) {
        if (entity == null) {
            return null;
        }
        ServiceDTO dto = new ServiceDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public Service toEntity(ServiceDTO dto) {
        if (dto == null) {
            return null;
        }
        Service entity = new Service();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    public List<ServiceDTO> toDtoList(List<Service> services) {
        return services.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
```

### 📄 src/main/java/org/group/koipondbackend/mapper/FeedbackMapper.java

```
package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.FeedbackDTO;
import org.group.koipondbackend.entity.Feedback;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FeedbackMapper {

    public FeedbackDTO toDto(Feedback feedback) {
        if (feedback == null) {
            return null;
        }
        FeedbackDTO dto = new FeedbackDTO();
        dto.setId(feedback.getId());
        dto.setCustomerId(feedback.getCustomerId());
        dto.setContent(feedback.getContent());
        dto.setRating(feedback.getRating());
        dto.setCreatedAt(feedback.getCreatedAt());
        return dto;
    }

    public Feedback toEntity(FeedbackDTO dto) {
        if (dto == null) {
            return null;
        }
        Feedback feedback = new Feedback();
        feedback.setId(dto.getId());
        feedback.setCustomerId(dto.getCustomerId());
        feedback.setContent(dto.getContent());
        feedback.setRating(dto.getRating());
        feedback.setCreatedAt(dto.getCreatedAt());
        return feedback;
    }

    public List<FeedbackDTO> toDtoList(List<Feedback> feedbacks) {
        return feedbacks.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
```

### 📄 src/main/java/org/group/koipondbackend/mapper/GenericMapper.java

```
package org.group.koipondbackend.mapper;

import java.util.List;

public interface GenericMapper<E, D> {
    public D toDto(E entity);

    public E toEntity(D dto);

    public default List<D> toDto(List<E> entityList) {
        return entityList.stream().map(this::toDto).toList();
    }

    public default List<E> toEntity(List<D> dtoList) {
        return dtoList.stream().map(this::toEntity).toList();
    }
}

```

### 📄 src/main/java/org/group/koipondbackend/mapper/OrderMapper.java

```
package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.OrderDTO;
import org.group.koipondbackend.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderDTO toDto(Order entity) {
        if (entity == null) {
            return null;
        }
        OrderDTO dto = new OrderDTO();
        dto.setId(entity.getId());
        dto.setProjectId(entity.getProjectId());
        dto.setOrderDetails(entity.getOrderDetails());
        dto.setAmount(entity.getAmount());
        return dto;
    }

    public Order toEntity(OrderDTO dto) {
        if (dto == null) {
            return null;
        }
        Order entity = new Order();
        entity.setId(dto.getId());
        entity.setProjectId(dto.getProjectId());
        entity.setOrderDetails(dto.getOrderDetails());
        entity.setAmount(dto.getAmount());
        return entity;
    }

    public List<OrderDTO> toDtoList(List<Order> orders) {
        return orders.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
```

### 📄 src/main/java/org/group/koipondbackend/mapper/ProjectMapper.java

```
package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.ProjectDTO;
import org.group.koipondbackend.entity.Project;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectMapper {

    // Chuyển đổi từ Project entity sang ProjectDTO
    public ProjectDTO toDto(Project entity) {
        if (entity == null) {
            return null;
        }

        ProjectDTO dto = new ProjectDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        // Thêm các thuộc tính khác nếu cần

        return dto;
    }

    // Chuyển đổi từ ProjectDTO sang Project entity
    public Project toEntity(ProjectDTO dto) {
        if (dto == null) {
            return null;
        }

        Project entity = new Project();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        // Thêm các thuộc tính khác nếu cần

        return entity;
    }

    // Chuyển đổi danh sách Project entity sang danh sách ProjectDTO
    public List<ProjectDTO> toDtoList(List<Project> projects) {
        return projects.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
```

### 📄 src/main/java/org/group/koipondbackend/mapper/ConstructionStaffMapper.java

```
package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.ConstructionStaffDTO;
import org.group.koipondbackend.entity.ConstructionStaff;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConstructionStaffMapper extends GenericMapper<ConstructionStaff, ConstructionStaffDTO> {

    ConstructionStaffDTO toDto(ConstructionStaff entity);

    ConstructionStaff toEntity(ConstructionStaffDTO dto);
}

```

### 📄 src/main/java/org/group/koipondbackend/mapper/DesignStaffMapper.java

```
package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.DesignStaffDTO;
import org.group.koipondbackend.entity.DesignStaff;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DesignStaffMapper extends GenericMapper<DesignStaff, DesignStaffDTO> {

    DesignStaffDTO toDto(DesignStaff entity);

    DesignStaff toEntity(DesignStaffDTO dto);
}

```

### 📄 src/main/java/org/group/koipondbackend/mapper/ConsultingStaffMapper.java

```
package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.ConsultingStaffDTO;
import org.group.koipondbackend.dto.ConsultingStaffDto;
import org.group.koipondbackend.entity.ConsultingStaff;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsultingStaffMapper extends GenericMapper<ConsultingStaff, ConsultingStaffDTO> {
    ConsultingStaffDTO toDto(ConsultingStaff entity);

    ConsultingStaff toEntity(ConsultingStaffDto dto);
}

```

### 📄 src/main/java/org/group/koipondbackend/controller/ServiceController.java

```
package org.group.koipondbackend.controller;

import org.group.koipondbackend.dto.ServiceDTO;
import org.group.koipondbackend.service.impl.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public List<ServiceDTO> getAllServices() {
        return serviceService.getAllServices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDTO> getServiceById(@PathVariable Long id) {
        return serviceService.getServiceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ServiceDTO> createService(@RequestBody ServiceDTO serviceDTO) {
        ServiceDTO createdService = serviceService.createService(serviceDTO);
        return ResponseEntity.created(URI.create("/api/services/" + createdService.getId()))
                .body(createdService);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceDTO> updateService(@PathVariable Long id, @RequestBody ServiceDTO serviceDTO) {
        return serviceService.updateService(id, serviceDTO)
                .map(updatedService -> ResponseEntity.ok(updatedService))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        if (serviceService.deleteService(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
```

### 📄 src/main/java/org/group/koipondbackend/controller/ProjectController.java

```
package org.group.koipondbackend.controller;

import org.group.koipondbackend.dto.ProjectDTO;
import org.group.koipondbackend.dto.OrderDTO; // Nhập OrderDTO
import org.group.koipondbackend.service.impl.OrderService; // Nhập OrderService
import org.group.koipondbackend.service.impl.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final OrderService orderService; // Khai báo OrderService

    @Autowired
    public ProjectController(ProjectService projectService, OrderService orderService) {
        this.projectService = projectService;
        this.orderService = orderService; // Inject OrderService
    }

    @GetMapping
    public List<ProjectDTO> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {
        ProjectDTO createdProject = projectService.createProject(projectDTO);
        return ResponseEntity.created(URI.create("/api/projects/" + createdProject.getId()))
                .body(createdProject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
        return projectService.updateProject(id, projectDTO)
                .map(updatedProject -> ResponseEntity.ok(updatedProject))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        if (projectService.deleteProject(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/orders")
    public List<OrderDTO> getOrdersByProjectId(@PathVariable Long id) {
        return orderService.getOrdersByProjectId(id); // Sử dụng orderService
    }
}
```

### 📄 src/main/java/org/group/koipondbackend/controller/StaffController.java

```
package org.group.koipondbackend.controller;

import lombok.RequiredArgsConstructor;

import org.group.koipondbackend.dto.CreateStaffRequest;
import org.group.koipondbackend.dto.StaffDto;
import org.group.koipondbackend.dto.UpdateStaffRequest;
import org.group.koipondbackend.service.staff.StaffService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class StaffController {
    private final StaffService staffService;

    @PostMapping
    public ResponseEntity<StaffDto> createStaff(@Valid @RequestBody CreateStaffRequest request) {
        return ResponseEntity.ok(staffService.createStaff(request));
    }

    @GetMapping
    public ResponseEntity<List<StaffDto>> getAllStaff() {
        return ResponseEntity.ok(staffService.getAllStaff());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffDto> getStaffById(@PathVariable Long id) {
        return ResponseEntity.ok(staffService.getStaffById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StaffDto> updateStaff(
            @PathVariable Long id,
            @Valid @RequestBody UpdateStaffRequest request) {
        return ResponseEntity.ok(staffService.updateStaff(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
        return ResponseEntity.noContent().build();
    }
}

```

### 📄 src/main/java/org/group/koipondbackend/controller/AuthController.java

```
package org.group.koipondbackend.controller;

import lombok.RequiredArgsConstructor;

import org.group.koipondbackend.dto.auth.LoginRequest;
import org.group.koipondbackend.dto.auth.LoginResponse;
import org.group.koipondbackend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}

```

### 📄 src/main/java/org/group/koipondbackend/controller/UserController.java

```
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

```

### 📄 src/main/java/org/group/koipondbackend/controller/FeedbackController.java

```
package org.group.koipondbackend.controller;

import org.group.koipondbackend.dto.FeedbackDTO;
import org.group.koipondbackend.service.impl.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public List<FeedbackDTO> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackDTO> getFeedbackById(@PathVariable Long id) {
        return feedbackService.getFeedbackById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public FeedbackDTO createFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        return feedbackService.createFeedback(feedbackDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackDTO> updateFeedback(@PathVariable Long id, @RequestBody FeedbackDTO feedbackDTO) {
        return feedbackService.updateFeedback(id, feedbackDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        return feedbackService.deleteFeedback(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/customers/{customerId}")
    public List<FeedbackDTO> getFeedbacksByCustomerId(@PathVariable Long customerId) {
        return feedbackService.getFeedbacksByCustomerId(customerId);
    }
}
```

### 📄 src/main/java/org/group/koipondbackend/dto/auth/UserInfo.java

```
package org.group.koipondbackend.dto.auth;

import org.group.koipondbackend.entity.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private Long id;
    private String email;
    private String fullName;
    private Role role;
}

```

### 📄 src/main/java/org/group/koipondbackend/dto/auth/LoginResponse.java

```
package org.group.koipondbackend.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private UserInfo user;
}

```

### 📄 src/main/java/org/group/koipondbackend/dto/auth/LoginRequest.java

```
package org.group.koipondbackend.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String email;
    private String password;
}

```

### 📄 src/main/java/org/group/koipondbackend/dto/auth/CreateUserRequest.java

```
package org.group.koipondbackend.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Phone is required")
    private String phone;

    private String address;
}

```

### 📄 src/main/java/org/group/koipondbackend/service/interfaces/UserService.java

```
package org.group.koipondbackend.service.interfaces;

import org.group.koipondbackend.dto.UserDTO;
import org.group.koipondbackend.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService<T extends User, D extends UserDTO> {
    D create(D dto);

    Optional<D> findById(Long id);

    List<D> findAll();

    D update(D dto);

    void delete(Long id);
}

```

### 📄 src/main/java/org/group/koipondbackend/service/impl/FeedbackService.java

```
package org.group.koipondbackend.service.impl;

import org.group.koipondbackend.dto.FeedbackDTO;
import org.group.koipondbackend.entity.Feedback;
import org.group.koipondbackend.mapper.FeedbackMapper;
import org.group.koipondbackend.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository, FeedbackMapper feedbackMapper) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackMapper = feedbackMapper;
    }

    public List<FeedbackDTO> getAllFeedbacks() {
        List<Feedback> feedbacks = feedbackRepository.findAll();
        return feedbackMapper.toDtoList(feedbacks);
    }

    public Optional<FeedbackDTO> getFeedbackById(Long id) {
        return feedbackRepository.findById(id)
                .map(feedbackMapper::toDto);
    }

    public FeedbackDTO createFeedback(FeedbackDTO feedbackDTO) {
        Feedback feedback = feedbackMapper.toEntity(feedbackDTO);
        Feedback savedFeedback = feedbackRepository.save(feedback);
        return feedbackMapper.toDto(savedFeedback);
    }

    public Optional<FeedbackDTO> updateFeedback(Long id, FeedbackDTO feedbackDTO) {
        return feedbackRepository.findById(id)
                .map(feedback -> {
                    feedback.setContent(feedbackDTO.getContent());
                    feedback.setRating(feedbackDTO.getRating());
                    return feedbackMapper.toDto(feedbackRepository.save(feedback));
                });
    }

    public boolean deleteFeedback(Long id) {
        if (feedbackRepository.existsById(id)) {
            feedbackRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<FeedbackDTO> getFeedbacksByCustomerId(Long customerId) {
        List<Feedback> feedbacks = feedbackRepository.findByCustomerId(customerId);
        return feedbackMapper.toDtoList(feedbacks);
    }
}
```

### 📄 src/main/java/org/group/koipondbackend/service/impl/ServiceService.java

```
package org.group.koipondbackend.service.impl;

import org.group.koipondbackend.dto.ServiceDTO;
import org.group.koipondbackend.entity.Service;
import org.group.koipondbackend.mapper.ServiceMapper;
import org.group.koipondbackend.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
@org.springframework.stereotype.Service
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository, ServiceMapper serviceMapper) {
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
    }

    public List<ServiceDTO> getAllServices() {
        List<Service> services = serviceRepository.findAll();
        return serviceMapper.toDtoList(services);
    }

    public Optional<ServiceDTO> getServiceById(Long id) {
        return serviceRepository.findById(id)
                .map(serviceMapper::toDto);
    }

    public ServiceDTO createService(ServiceDTO serviceDTO) {
        Service service = serviceMapper.toEntity(serviceDTO);
        Service savedService = serviceRepository.save(service);
        return serviceMapper.toDto(savedService);
    }

    public Optional<ServiceDTO> updateService(Long id, ServiceDTO serviceDTO) {
        return serviceRepository.findById(id)
                .map(service -> {
                    service.setName(serviceDTO.getName());
                    service.setDescription(serviceDTO.getDescription());
                    return serviceMapper.toDto(serviceRepository.save(service));
                });
    }

    public boolean deleteService(Long id) {
        if (serviceRepository.existsById(id)) {
            serviceRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
```

### 📄 src/main/java/org/group/koipondbackend/service/impl/ProjectService.java

```
package org.group.koipondbackend.service.impl;

import org.group.koipondbackend.dto.ProjectDTO;
import org.group.koipondbackend.entity.Project;
import org.group.koipondbackend.mapper.ProjectMapper;
import org.group.koipondbackend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    public List<ProjectDTO> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projectMapper.toDtoList(projects);
    }

    public Optional<ProjectDTO> getProjectById(Long id) {
        return projectRepository.findById(id)
                .map(projectMapper::toDto);
    }

    public ProjectDTO createProject(ProjectDTO projectDTO) {
        Project project = projectMapper.toEntity(projectDTO);
        Project savedProject = projectRepository.save(project);
        return projectMapper.toDto(savedProject);
    }

    public Optional<ProjectDTO> updateProject(Long id, ProjectDTO projectDTO) {
        return projectRepository.findById(id)
                .map(project -> {
                    // Cập nhật thông tin dự án
                    return projectMapper.toDto(projectRepository.save(project));
                });
    }

    public boolean deleteProject(Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
```

### 📄 src/main/java/org/group/koipondbackend/service/impl/OrderService.java

```
package org.group.koipondbackend.service.impl;

import org.group.koipondbackend.dto.OrderDTO;
import org.group.koipondbackend.entity.Order;
import org.group.koipondbackend.mapper.OrderMapper;
import org.group.koipondbackend.repository.OrderRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderDTO> getOrdersByProjectId(Long projectId) {
        List<Order> orders = orderRepository.findByProjectId(projectId);
        return orders.stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }
}

```

### 📄 src/main/java/org/group/koipondbackend/service/impl/AbstractUserService.java

```
package org.group.koipondbackend.service.impl;

import java.util.List;
import java.util.Optional;

import org.group.koipondbackend.dto.UserDTO;
import org.group.koipondbackend.entity.User;
import org.group.koipondbackend.mapper.GenericMapper;
import org.group.koipondbackend.repository.UserRepository;
import org.group.koipondbackend.service.interfaces.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AbstractUserService<T extends User, D extends UserDTO>
        implements UserService<T, D> {

    protected final UserRepository<T> repository;
    protected final GenericMapper<T, D> mapper;

    @Override
    public D create(D dto) {
        T entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public Optional<D> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public List<D> findAll() {
        return mapper.toDto(repository.findAll());
    }

    @Override
    public D update(D dto) {
        T entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

```

### 📄 src/main/java/org/group/koipondbackend/service/staff/StaffService.java

```
package org.group.koipondbackend.service.staff;

import lombok.RequiredArgsConstructor;

import org.group.koipondbackend.dto.CreateStaffRequest;
import org.group.koipondbackend.dto.StaffDto;
import org.group.koipondbackend.dto.UpdateStaffRequest;
import org.group.koipondbackend.entity.Staff;
import org.group.koipondbackend.entity.enums.Role;
import org.group.koipondbackend.exception.ResourceNotFoundException;
import org.group.koipondbackend.repository.StaffRepository;
import org.group.koipondbackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StaffService {
    private final StaffRepository staffRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public StaffDto createStaff(CreateStaffRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Staff staff = Staff.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .phone(request.getPhone())
                .address(request.getAddress())
                .role(Role.STAFF)
                .department(request.getDepartment())
                .status("ACTIVE")
                .joinDate(LocalDateTime.now())
                .build();

        Staff savedStaff = staffRepository.save(staff);
        return mapToDto(savedStaff);
    }

    @Transactional(readOnly = true)
    public List<StaffDto> getAllStaff() {
        return staffRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public StaffDto getStaffById(Long id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + id));
        return mapToDto(staff);
    }

    @Transactional
    public StaffDto updateStaff(Long id, UpdateStaffRequest request) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + id));

        staff.setFullName(request.getFullName());
        staff.setPhone(request.getPhone());
        staff.setAddress(request.getAddress());
        staff.setDepartment(request.getDepartment());

        Staff updatedStaff = staffRepository.save(staff);
        return mapToDto(updatedStaff);
    }

    @Transactional
    public void deleteStaff(Long id) {
        if (!staffRepository.existsById(id)) {
            throw new ResourceNotFoundException("Staff not found with id: " + id);
        }
        staffRepository.deleteById(id);
    }

    private StaffDto mapToDto(Staff staff) {
        return StaffDto.builder()
                .id(staff.getId())
                .email(staff.getEmail())
                .fullName(staff.getFullName())
                .phone(staff.getPhone())
                .address(staff.getAddress())
                .role(staff.getRole())
                .department(staff.getDepartment())
                .status(staff.getStatus())
                .joinDate(staff.getJoinDate())
                .build();
    }
}

```

### 📄 src/main/java/org/group/koipondbackend/entity/enums/Role.java

```
package org.group.koipondbackend.entity.enums;

public enum Role {
    ADMIN,
    STAFF,
    CONSULTING_STAFF,
    DESIGN_STAFF,
    CONSTRUCTION_STAFF,
    CUSTOMER
}

```

