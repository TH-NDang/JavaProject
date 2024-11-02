package org.group.koipondbackend.dto;

import lombok.Data;

@Data
public class ServiceDTO {
    private Long id;
    private String name, description;
    private double price;

    public ServiceDTO(Long id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public ServiceDTO() {}
}
