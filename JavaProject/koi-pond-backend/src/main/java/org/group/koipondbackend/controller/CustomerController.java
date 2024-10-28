package org.group.koipondbackend.controller;

import java.util.List;
import org.group.koipondbackend.dto.CustomerDTO;
import org.group.koipondbackend.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.group.koipondbackend.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    // Lấy danh sách tất cả khách hàng
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    // Lấy thông tin chi tiết của một khách hàng theo id
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        CustomerDTO customer = customerService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    // Tạo mới một khách hàng
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDto) {
        CustomerDTO createdCustomer = customerService.create(customerDto);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    // Cập nhật thông tin của một khách hàng
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDto) {
        customerDto.setId(id);
        CustomerDTO updatedCustomer = customerService.update(customerDto);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    // Xóa một khách hàng
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.ok("Customer deleted successfully");
    }
}
