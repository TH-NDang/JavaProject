package org.group.koipondbackend.dto;

import lombok.Data;
import java.time.LocalDateTime;  // Thêm import này

@Data
public class ProjectDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdDate;  // Thêm trường này
}
