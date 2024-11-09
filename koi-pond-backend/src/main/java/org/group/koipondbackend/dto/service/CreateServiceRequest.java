package org.group.koipondbackend.dto.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.List;

@Data
public class CreateServiceRequest {
    @NotBlank(message = "Tên dịch vụ không được để trống")
    private String name;

    @NotBlank(message = "Mô tả không được để trống")
    private String description;

    @NotNull(message = "Giá không được để trống")
    private Double price;

    @Size(min = 1, message = "Phải có ít nhất một tính năng")
    private List<String> features;

    private Boolean isPopular;
}
