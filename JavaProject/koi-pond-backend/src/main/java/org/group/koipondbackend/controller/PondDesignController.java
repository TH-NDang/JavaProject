package org.group.koipondbackend.controller;

import org.group.koipondbackend.dto.PondDesignDTO;
import org.group.koipondbackend.service.PondDesignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pond-designs")
public class PondDesignController {

    @Autowired
    private PondDesignService pondDesignService;

    @GetMapping
    public List<PondDesignDTO> getAllPondDesigns() {
        return pondDesignService.getAllPondDesigns();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PondDesignDTO> getPondDesignById(@PathVariable Long id) {
        PondDesignDTO dto = pondDesignService.getPondDesignById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PondDesignDTO> createPondDesign(@RequestBody PondDesignDTO dto) {
        PondDesignDTO createdDto = pondDesignService.createPondDesign(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PondDesignDTO> updatePondDesign(@PathVariable Long id, @RequestBody PondDesignDTO dto) {
        PondDesignDTO updatedDto = pondDesignService.updatePondDesign(id, dto);
        return updatedDto != null ? ResponseEntity.ok(updatedDto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePondDesign(@PathVariable Long id) {
        return pondDesignService.deletePondDesign(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/cost-estimate")
    public ResponseEntity<Double> estimateCost(@PathVariable Long id) {
        Double costEstimate = pondDesignService.estimateCost(id);
        return costEstimate != null ? ResponseEntity.ok(costEstimate) : ResponseEntity.notFound().build();
    }

    // Bổ sung endpoint để áp dụng thiết kế cho một hồ cá Koi
    @PostMapping("/pond/{pondId}/apply")
    public ResponseEntity<Void> applyDesignToPond(@PathVariable Long pondId, @RequestBody PondDesignDTO designDto) {
        pondDesignService.applyDesignToPond(pondId, designDto);
        return ResponseEntity.ok().build();
    }
}
