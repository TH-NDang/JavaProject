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