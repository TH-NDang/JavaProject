package org.group.koipondbackend.controller;

import java.util.List;

import org.group.koipondbackend.dto.CustomerDTO;
import org.group.koipondbackend.service.impl.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.group.koipondbackend.exception.ResourceNotFoundException;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDto) {
        CustomerDTO createdCustomer = customerService.create(customerDto);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        CustomerDTO customer = customerService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDto) {
        customerDto.setId(id);
        CustomerDTO updatedCustomer = customerService.update(customerDto);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.ok("Customer deleted successfully");
    }
}
