package org.group.koipondbackend.dto;

import lombok.Data;

@Data
public class PondDesignDTO {
    private Long id;
    private String name;
    private String description;
    private Double costEstimate; // Ước tính chi phí
}
