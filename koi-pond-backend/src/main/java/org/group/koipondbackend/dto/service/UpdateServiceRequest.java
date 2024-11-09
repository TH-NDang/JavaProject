package org.group.koipondbackend.dto.service;

import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.List;

@Data
public class UpdateServiceRequest {
    private String name;
    private String description;
    private Double price;

    @Size(min = 1, message = "Phải có ít nhất một tính năng")
    private List<String> features;

    private Boolean isPopular;
}
