package org.group.koipondbackend.service.impl;

import org.group.koipondbackend.dto.CustomerDTO;
import org.group.koipondbackend.entity.Customer;
import org.group.koipondbackend.exception.ResourceNotFoundException;
import org.group.koipondbackend.mapper.CustomerMapper;
import org.group.koipondbackend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public List<CustomerDTO> findAll() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
    }

    public CustomerDTO findById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
    }

    public CustomerDTO create(CustomerDTO customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toDto(savedCustomer);
    }

    public CustomerDTO update(Long id, CustomerDTO customerDto) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));

        existingCustomer.setName(customerDto.getName());
        existingCustomer.setEmail(customerDto.getEmail());
        existingCustomer.setPhoneNumber(customerDto.getPhoneNumber());
        existingCustomer.setAddress(customerDto.getAddress());

        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return customerMapper.toDto(updatedCustomer);
    }

    public void delete(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer not found with id " + id);
        }
        customerRepository.deleteById(id);
    }
}
