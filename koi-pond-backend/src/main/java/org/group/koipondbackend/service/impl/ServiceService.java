package org.group.koipondbackend.service.impl;

import org.group.koipondbackend.dto.ServiceDTO;
import org.group.koipondbackend.mapper.ServiceMapper;
import org.group.koipondbackend.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceService {

    private final ServiceRepository repository;
    private final ServiceMapper mapper;

    public ServiceDTO create(ServiceDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public ServiceDTO findById(Long id) {
        return mapper.toDto(repository.findById(id).orElse(null));
    }

    public List<ServiceDTO> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    public ServiceDTO update(Long id, ServiceDTO dto) {
        dto.setId(id);
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
