package org.group.koipondbackend.controller;

import org.group.koipondbackend.dto.ServiceDTO;
import org.group.koipondbackend.service.impl.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public List<ServiceDTO> getAllServices() {
        return serviceService.getAllServices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDTO> getServiceById(@PathVariable Long id) {
        return serviceService.getServiceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ServiceDTO> createService(@RequestBody ServiceDTO serviceDTO) {
        ServiceDTO createdService = serviceService.createService(serviceDTO);
        return ResponseEntity.created(URI.create("/api/services/" + createdService.getId()))
                .body(createdService);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceDTO> updateService(@PathVariable Long id, @RequestBody ServiceDTO serviceDTO) {
        return serviceService.updateService(id, serviceDTO)
                .map(updatedService -> ResponseEntity.ok(updatedService))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        if (serviceService.deleteService(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}