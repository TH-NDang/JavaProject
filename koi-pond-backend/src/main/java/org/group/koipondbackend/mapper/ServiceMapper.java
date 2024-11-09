package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.service.*;
import org.group.koipondbackend.entity.Services;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ServiceMapper {

    ServiceDTO toDto(Services service);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Services toEntity(CreateServiceRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(UpdateServiceRequest request, @MappingTarget Services service);
}
