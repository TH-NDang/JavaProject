package org.group.koipondbackend.controller;

import org.group.koipondbackend.dto.CustomerDTO;
import org.group.koipondbackend.service.impl.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping public List<CustomerDTO> getAll() { return customerService.findAll(); }
    @GetMapping("/{id}") public CustomerDTO getById(@PathVariable Long id) { return customerService.findById(id); }
    @PostMapping public CustomerDTO create(@RequestBody CustomerDTO dto) { return customerService.create(dto); }
    @PutMapping("/{id}") public CustomerDTO update(@PathVariable Long id, @RequestBody CustomerDTO dto) { return customerService.update(id, dto); }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { customerService.delete(id); }
}
