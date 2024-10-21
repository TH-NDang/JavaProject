package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.MaintenanceServiceDTO;
import org.group.koipondbackend.entity.MaintenanceService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MaintenanceServiceMapper {

    public MaintenanceServiceDTO toDto(MaintenanceService entity) {
        if (entity == null) {
            return null;
        }

        MaintenanceServiceDTO dto = new MaintenanceServiceDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());

        return dto;
    }

    public MaintenanceService toEntity(MaintenanceServiceDTO dto) {
        if (dto == null) {
            return null;
        }

        MaintenanceService entity = new MaintenanceService();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());

        return entity;
    }

    public List<MaintenanceServiceDTO> toDtoList(List<MaintenanceService> maintenanceServices) {
        return maintenanceServices.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
