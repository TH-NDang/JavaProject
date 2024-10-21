package org.group.koipondbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "maintenance_services")
@Data
public class MaintenanceService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;        // Tên dịch vụ bảo dưỡng
    private String description; // Mô tả dịch vụ
    private Double price;       // Giá dịch vụ

    // Quan hệ với KoiPond để liên kết dịch vụ bảo dưỡng với hồ cá Koi
    @ManyToOne
    @JoinColumn(name = "koi_pond_id")
    private KoiPond koiPond;
}
