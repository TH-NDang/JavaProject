package org.group.koipondbackend.dto;

import lombok.Data;
import java.util.List;

@Data
public class KoiPondDTO {
    private Long id;
    private String name;
    private Double sizeInLiters;
    private Double depthInMeters;
    private String shape;
    private String filtrationSystem;
    private String waterQuality;
    private String description;
    private List<String> imagePaths;
}
