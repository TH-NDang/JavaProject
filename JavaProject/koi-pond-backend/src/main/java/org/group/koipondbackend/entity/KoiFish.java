package org.group.koipondbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "koi_fish")
@Data
public class KoiFish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;        // Tên cá Koi
    private String color;       // Màu sắc cá Koi
    private String size;        // Kích thước cá Koi

    @ManyToOne
    @JoinColumn(name = "pond_id", nullable = false)
    private KoiPond pond;      // Quan hệ với hồ Koi
}
