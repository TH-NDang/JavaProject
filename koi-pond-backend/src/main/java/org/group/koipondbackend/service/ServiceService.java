// src/main/java/org/group/koipondbackend/service/ServiceService.java
package org.group.koipondbackend.service;

import lombok.RequiredArgsConstructor;
import org.group.koipondbackend.dto.service.ServiceDTO;
import org.group.koipondbackend.dto.service.CreateServiceRequest;
import org.group.koipondbackend.dto.service.UpdateServiceRequest;
import org.group.koipondbackend.entity.Services;
import org.group.koipondbackend.exception.BadRequestException;
import org.group.koipondbackend.exception.ResourceNotFoundException;
import org.group.koipondbackend.mapper.ServiceMapper;
import org.group.koipondbackend.repository.ServicesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceService {
    private final ServicesRepository servicesRepository;
    private final ServiceMapper serviceMapper;

    @Transactional(readOnly = true)
    public List<ServiceDTO> getAllServices() {
        return servicesRepository.findAll().stream()
                .map(serviceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ServiceDTO getServiceById(Long id) {
        Services service = findServiceById(id);
        return serviceMapper.toDto(service);
    }

    @Transactional
    public ServiceDTO createService(CreateServiceRequest request) {
        if (servicesRepository.existsByName(request.getName())) {
            throw new BadRequestException("Dịch vụ với tên này đã tồn tại");
        }

        Services service = serviceMapper.toEntity(request);
        service = servicesRepository.save(service);
        return serviceMapper.toDto(service);
    }

    @Transactional
    public ServiceDTO updateService(Long id, UpdateServiceRequest request) {
        Services service = findServiceById(id);

        if (request.getName() != null && !request.getName().equals(service.getName()) &&
                servicesRepository.existsByName(request.getName())) {
            throw new BadRequestException("Dịch vụ với tên này đã tồn tại");
        }

        serviceMapper.updateEntityFromDto(request, service);
        service = servicesRepository.save(service);
        return serviceMapper.toDto(service);
    }

    @Transactional
    public void deleteService(Long id) {
        if (!servicesRepository.existsById(id)) {
            throw new ResourceNotFoundException("Không tìm thấy dịch vụ");
        }
        servicesRepository.deleteById(id);
    }

    private Services findServiceById(Long id) {
        return servicesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy dịch vụ"));
    }
}
