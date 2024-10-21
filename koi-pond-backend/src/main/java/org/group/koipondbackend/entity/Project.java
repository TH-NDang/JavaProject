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