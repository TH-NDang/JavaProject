package org.group.koipondbackend.dto;

import lombok.Data;

@Data
public class ServiceDTO {
    private Long id;              // ID của dịch vụ
    private String name;          // Tên dịch vụ
    private String description;    // Mô tả dịch vụ
}