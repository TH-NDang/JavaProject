package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.service.ServiceDTO;
import org.group.koipondbackend.dto.service.CreateServiceRequest;
import org.group.koipondbackend.dto.service.UpdateServiceRequest;
import org.group.koipondbackend.entity.Services;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "features", source = "features")
    @Mapping(target = "isPopular", source = "isPopular")
    ServiceDTO toDto(Services services);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "features", source = "features")
    @Mapping(target = "isPopular", source = "isPopular")
    Services toEntity(CreateServiceRequest request);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(UpdateServiceRequest request, @MappingTarget Services services);
}
