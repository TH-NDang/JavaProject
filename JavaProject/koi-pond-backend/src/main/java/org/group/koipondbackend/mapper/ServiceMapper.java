package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.ServiceDTO;
import org.group.koipondbackend.entity.Service;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper extends BaseMapper<Service, ServiceDTO> {
    @Override
    public ServiceDTO toDto(Service entity) {
        if (entity == null) {
            return null;
        }

        ServiceDTO dto = new ServiceDTO();
        dto.setId(entity.getId());
        dto.setServiceName(entity.getServiceName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());

        return dto;
    }

    @Override
    public Service toEntity(ServiceDTO dto) {
        if (dto == null) {
            return null;
        }

        Service entity = new Service();
        entity.setId(dto.getId());
        entity.setServiceName(dto.getServiceName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());

        return entity;
    }
}
