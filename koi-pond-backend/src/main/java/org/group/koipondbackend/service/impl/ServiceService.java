package org.group.koipondbackend.service.impl;

import org.group.koipondbackend.dto.ServiceDTO;
import org.group.koipondbackend.entity.Service;
import org.group.koipondbackend.mapper.ServiceMapper;
import org.group.koipondbackend.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
@org.springframework.stereotype.Service
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository, ServiceMapper serviceMapper) {
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
    }

    public List<ServiceDTO> getAllServices() {
        List<Service> services = serviceRepository.findAll();
        return serviceMapper.toDtoList(services);
    }

    public Optional<ServiceDTO> getServiceById(Long id) {
        return serviceRepository.findById(id)
                .map(serviceMapper::toDto);
    }

    public ServiceDTO createService(ServiceDTO serviceDTO) {
        Service service = serviceMapper.toEntity(serviceDTO);
        Service savedService = serviceRepository.save(service);
        return serviceMapper.toDto(savedService);
    }

    public Optional<ServiceDTO> updateService(Long id, ServiceDTO serviceDTO) {
        return serviceRepository.findById(id)
                .map(service -> {
                    service.setName(serviceDTO.getName());
                    service.setDescription(serviceDTO.getDescription());
                    return serviceMapper.toDto(serviceRepository.save(service));
                });
    }

    public boolean deleteService(Long id) {
        if (serviceRepository.existsById(id)) {
            serviceRepository.deleteById(id);
            return true;
        }
        return false;
    }
}