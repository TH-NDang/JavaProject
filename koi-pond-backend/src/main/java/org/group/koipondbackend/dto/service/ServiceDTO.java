package org.group.koipondbackend.dto.service;

import lombok.Data;
import java.util.List;

@Data
public class ServiceDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private List<String> features;
    private Boolean isPopular;
}
