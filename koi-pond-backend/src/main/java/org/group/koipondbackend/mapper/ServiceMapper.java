package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.ServiceDTO;
import org.group.koipondbackend.entity.Services;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring")
public interface ServiceMapper {
    ServiceDTO toDto(Services services);

    Services toEntity(ServiceDTO serviceDTO);

    default List<ServiceDTO> toDtoList(List<Services> services) {
        return services.stream().map(this::toDto).collect(Collectors.toList());
    }
}
