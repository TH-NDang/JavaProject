package org.group.koipondbackend.service.impl;

import org.group.koipondbackend.dto.ServiceDTO;
import org.group.koipondbackend.entity.Service;
import org.group.koipondbackend.mapper.ServiceMapper;
import org.group.koipondbackend.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository, ServiceMapper serviceMapper) {
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
    }

    public List<ServiceDTO> findAll() {
        List<Service> services = serviceRepository.findAll();
        return serviceMapper.toDto(services);
    }

    public Optional<ServiceDTO> findById(Long id) {
        return serviceRepository.findById(id).map(serviceMapper::toDto);
    }

    public ServiceDTO create(ServiceDTO serviceDto) {
        Service service = serviceMapper.toEntity(serviceDto);
        Service savedService = serviceRepository.save(service);
        return serviceMapper.toDto(savedService);
    }

    public ServiceDTO update(ServiceDTO serviceDto) {
        if (serviceRepository.existsById(serviceDto.getId())) {
            Service service = serviceMapper.toEntity(serviceDto);
            Service updatedService = serviceRepository.save(service);
            return serviceMapper.toDto(updatedService);
        }
        return null; // Hoặc bạn có thể ném một exception ở đây
    }

    public void delete(Long id) {
        if (serviceRepository.existsById(id)) {
            serviceRepository.deleteById(id);
        }
    }
}
