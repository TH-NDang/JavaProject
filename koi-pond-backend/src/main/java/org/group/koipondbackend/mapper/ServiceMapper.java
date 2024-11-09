package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.service.ServiceDTO;
import org.group.koipondbackend.dto.service.CreateServiceRequest;
import org.group.koipondbackend.dto.service.UpdateServiceRequest;
import org.group.koipondbackend.entity.Services;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ServiceMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    ServiceDTO toDto(Services services);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    Services toEntity(CreateServiceRequest request);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(UpdateServiceRequest request, @MappingTarget Services services);
}
