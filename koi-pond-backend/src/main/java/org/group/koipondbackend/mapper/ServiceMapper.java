package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.service.ServiceDTO;
import org.group.koipondbackend.dto.service.CreateServiceRequest;
import org.group.koipondbackend.dto.service.UpdateServiceRequest;
import org.group.koipondbackend.entity.Services;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class ServiceMapper {

    public ServiceDTO toDto(Services service) {
        if (service == null) {
            return null;
        }

        return ServiceDTO.builder()
                .id(service.getId())
                .name(service.getName())
                .description(service.getDescription())
                .price(service.getPrice())
                .features(service.getFeatures() != null ? new ArrayList<>(service.getFeatures()) : new ArrayList<>())
                .isPopular(service.getIsPopular())
                .build();
    }

    public Services toEntity(CreateServiceRequest request) {
        if (request == null) {
            return null;
        }

        return Services.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .features(request.getFeatures() != null ? new ArrayList<>(request.getFeatures()) : new ArrayList<>())
                .isPopular(request.getIsPopular())
                .build();
    }

    public void updateEntityFromDto(UpdateServiceRequest request, Services service) {
        if (request == null) {
            return;
        }

        if (request.getName() != null) {
            service.setName(request.getName());
        }
        if (request.getDescription() != null) {
            service.setDescription(request.getDescription());
        }
        if (request.getPrice() != null) {
            service.setPrice(request.getPrice());
        }
        if (request.getFeatures() != null) {
            service.setFeatures(new ArrayList<>(request.getFeatures()));
        }
        if (request.getIsPopular() != null) {
            service.setIsPopular(request.getIsPopular());
        }
    }
}
