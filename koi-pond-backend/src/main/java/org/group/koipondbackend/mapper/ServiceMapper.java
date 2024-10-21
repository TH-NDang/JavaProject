package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.ServiceDTO;
import org.group.koipondbackend.entity.Service;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceMapper {

    public ServiceDTO toDto(Service entity) {
        if (entity == null) {
            return null;
        }
        ServiceDTO dto = new ServiceDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public Service toEntity(ServiceDTO dto) {
        if (dto == null) {
            return null;
        }
        Service entity = new Service();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    public List<ServiceDTO> toDtoList(List<Service> services) {
        return services.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}