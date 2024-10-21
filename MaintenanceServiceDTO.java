package org.group.koipondbackend.dto;

import lombok.Data;

@Data
public class MaintenanceServiceDTO {
    private Long id;
    private String name;
    private String description;
    private Double price; // Giá dịch vụ
}
