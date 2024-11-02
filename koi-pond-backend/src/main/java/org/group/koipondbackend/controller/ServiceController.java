package org.group.koipondbackend.controller;

import org.group.koipondbackend.dto.ServiceDTO;
import org.group.koipondbackend.service.impl.ServiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping public List<ServiceDTO> getAll() { return serviceService.findAll(); }
    @GetMapping("/{id}") public ServiceDTO getById(@PathVariable Long id) { return serviceService.findById(id); }
    @PostMapping public ServiceDTO create(@RequestBody ServiceDTO dto) { return serviceService.create(dto); }
    @PutMapping("/{id}") public ServiceDTO update(@PathVariable Long id, @RequestBody ServiceDTO dto) { return serviceService.update(id, dto); }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { serviceService.delete(id); }
}
