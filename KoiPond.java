package org.group.koipondbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "koi_ponds")
@Data
public class KoiPond {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "pond", cascade = CascadeType.ALL) // Quan hệ một đến nhiều
    private List<KoiFish> koiFishList; // Danh sách cá Koi trong hồ này

    // Thêm các trường mới vào lớp KoiPond
    private Double sizeInLiters;
    private Double depthInMeters;
    private String shape;
    private String filtrationSystem;
    private String waterQuality;
    private String description;

    @ElementCollection
    private List<String> imagePaths;

    // Quan hệ với PondDesign
    @ManyToOne
    @JoinColumn(name = "pond_design_id")
    private PondDesign pondDesign;

    // Quan hệ với MaintenanceService
    @OneToMany(mappedBy = "koiPond", cascade = CascadeType.ALL)
    private List<MaintenanceService> maintenanceServices;
}
