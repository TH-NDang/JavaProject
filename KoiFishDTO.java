package org.group.koipondbackend.dto;

import lombok.Data;

@Data
public class KoiFishDTO {
    private Long id;             // ID của cá Koi
    private String name;        // Tên cá Koi
    private String color;       // Màu sắc cá Koi
    private String size;        // Kích thước cá Koi
    private Long pondId;        // ID của hồ mà cá Koi thuộc về
}
