package org.group.koipondbackend.service.impl;

import org.group.koipondbackend.dto.CustomerDTO;
import org.group.koipondbackend.entity.Customer;
import org.group.koipondbackend.mapper.CustomerMapper;
import org.group.koipondbackend.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public CustomerService(CustomerRepository repository, CustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public CustomerDTO create(CustomerDTO dto) { return mapper.toDto(repository.save(mapper.toEntity(dto))); }
    public CustomerDTO findById(Long id) { return mapper.toDto(repository.findById(id).orElse(null)); }
    public List<CustomerDTO> findAll() { return mapper.toDtoList(repository.findAll()); }
    public CustomerDTO update(Long id, CustomerDTO dto) {
        dto.setId(id);
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }
    public void delete(Long id) { repository.deleteById(id); }
}
