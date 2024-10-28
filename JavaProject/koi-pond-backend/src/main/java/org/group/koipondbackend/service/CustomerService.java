package org.group.koipondbackend.service;

import java.util.List;
import java.util.Optional;
import org.group.koipondbackend.dto.CustomerDTO;

public interface CustomerService {
    List<CustomerDTO> findAll();
    Optional<CustomerDTO> findById(Long id);
    CustomerDTO create(CustomerDTO customerDto);
    CustomerDTO update(CustomerDTO customerDto);
    void delete(Long id);
}
