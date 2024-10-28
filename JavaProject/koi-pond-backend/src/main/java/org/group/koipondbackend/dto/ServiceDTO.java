package org.group.koipondbackend.dto;

import lombok.Data;

@Data
public class ServiceDTO {
    private Long id;
    private String serviceName;    // Tên dịch vụ
    private String description;     // Mô tả dịch vụ
    private Double price;           // Giá dịch vụ
}
