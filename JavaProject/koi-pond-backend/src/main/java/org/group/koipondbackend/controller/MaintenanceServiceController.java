package org.group.koipondbackend.controller;

import org.group.koipondbackend.dto.MaintenanceServiceDTO;
import org.group.koipondbackend.service.MaintenanceServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance-services")
public class MaintenanceServiceController {

    @Autowired
    private MaintenanceServiceService maintenanceServiceService;

    @GetMapping
    public List<MaintenanceServiceDTO> getAllMaintenanceServices() {
        return maintenanceServiceService.getAllMaintenanceServices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceServiceDTO> getMaintenanceServiceById(@PathVariable Long id) {
        MaintenanceServiceDTO dto = maintenanceServiceService.getMaintenanceServiceById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<MaintenanceServiceDTO> createMaintenanceService(@RequestBody MaintenanceServiceDTO dto) {
        MaintenanceServiceDTO createdDto = maintenanceServiceService.createMaintenanceService(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaintenanceServiceDTO> updateMaintenanceService(@PathVariable Long id, @RequestBody MaintenanceServiceDTO dto) {
        MaintenanceServiceDTO updatedDto = maintenanceServiceService.updateMaintenanceService(id, dto);
        return updatedDto != null ? ResponseEntity.ok(updatedDto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaintenanceService(@PathVariable Long id) {
        return maintenanceServiceService.deleteMaintenanceService(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/schedule")
    public ResponseEntity<Void> scheduleMaintenance(@PathVariable Long id, @RequestBody String scheduleDetails) {
        maintenanceServiceService.scheduleMaintenance(id, scheduleDetails);
        return ResponseEntity.ok().build();
    }

    // Bổ sung endpoint để thêm dịch vụ bảo dưỡng cho một hồ cá Koi
    @PostMapping("/pond/{pondId}/add")
    public ResponseEntity<MaintenanceServiceDTO> addMaintenanceServiceToPond(@PathVariable Long pondId, @RequestBody MaintenanceServiceDTO dto) {
        MaintenanceServiceDTO createdDto = maintenanceServiceService.addServiceToPond(pondId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }
}
