package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.ServiceDTO;
import org.group.koipondbackend.entity.Services;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceMapper {

    public ServiceDTO toDto(Services s) {
        return new ServiceDTO(s.getId(), s.getName(), s.getDescription(), s.getPrice());
    }

    public Services toEntity(ServiceDTO dto) {
        Services s = new Services();
        s.setId(dto.getId());
        s.setName(dto.getName());
        s.setDescription(dto.getDescription());
        s.setPrice(dto.getPrice());
        return s;
    }

    public List<ServiceDTO> toDtoList(List<Services> services) {
        return services.stream().map(this::toDto).collect(Collectors.toList());
    }
}
