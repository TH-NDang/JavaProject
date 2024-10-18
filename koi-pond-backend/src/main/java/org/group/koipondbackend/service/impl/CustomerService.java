package org.group.koipondbackend.service.impl;

import org.group.koipondbackend.dto.CustomerDTO;
import org.group.koipondbackend.entity.Customer;
import org.group.koipondbackend.mapper.CustomerMapper;
import org.group.koipondbackend.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends AbstractUserService<Customer, CustomerDTO> {
    public CustomerService(CustomerRepository repository, CustomerMapper mapper) {
        super(repository, mapper);
    }
}
