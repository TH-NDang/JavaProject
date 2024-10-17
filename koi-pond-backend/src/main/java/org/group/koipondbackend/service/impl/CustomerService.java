package org.group.koipondbackend.service.impl;

import org.group.koipondbackend.dto.CustomerDTO;
import org.group.koipondbackend.entity.Customer;
import org.group.koipondbackend.mapper.CustomerMapper;
import org.group.koipondbackend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService extends AbstractUserService<Customer, CustomerDTO> {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository repository, CustomerMapper mapper) {
        super(repository, mapper);
        this.customerRepository = repository;
        this.customerMapper = mapper;
    }

    public Optional<CustomerDTO> getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::toDto);
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toDto(savedCustomer);
    }

    public Optional<CustomerDTO> updateCustomer(Long id, CustomerDTO customerDetails) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(customerDetails.getName());
                    customer.setEmail(customerDetails.getEmail());
                    customer.setPhone(customerDetails.getPhone());
                    customer.setAddress(customerDetails.getAddress()); // Cập nhật địa chỉ
                    return customerMapper.toDto(customerRepository.save(customer));
                });
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}