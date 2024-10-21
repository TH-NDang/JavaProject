package org.group.koipondbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;  // Thêm import này

@Entity
@Table(name = "projects")
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private LocalDateTime createdDate;  // Thêm trường này

    // Getters and Setters nếu cần
}
