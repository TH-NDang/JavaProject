package org.group.koipondbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "pond_designs")
@Data
public class PondDesign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;        // Tên mẫu thiết kế
    private String description; // Mô tả mẫu thiết kế
    private Double costEstimate; // Ước tính chi phí

    // Quan hệ với KoiPond
    @OneToMany(mappedBy = "pondDesign")
    private List<KoiPond> koiPonds;
}
