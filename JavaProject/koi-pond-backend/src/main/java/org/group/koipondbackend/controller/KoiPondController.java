package org.group.koipondbackend.controller;

import org.group.koipondbackend.dto.KoiPondDTO;
import org.group.koipondbackend.service.KoiPondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/koi-ponds")
public class KoiPondController {

    @Autowired
    private KoiPondService koiPondService;

    @GetMapping
    public List<KoiPondDTO> getAllPonds() {
        return koiPondService.getAllPonds();
    }

    @GetMapping("/{id}")
    public ResponseEntity<KoiPondDTO> getPondById(@PathVariable Long id) {
        KoiPondDTO pond = koiPondService.getPondById(id);
        return ResponseEntity.ok(pond);
    }

    @PostMapping
    public ResponseEntity<KoiPondDTO> createPond(@RequestBody KoiPondDTO pondDTO) {
        KoiPondDTO createdPond = koiPondService.createPond(pondDTO);
        return ResponseEntity.ok(createdPond);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KoiPondDTO> updatePond(@PathVariable Long id, @RequestBody KoiPondDTO pondDTO) {
        KoiPondDTO updatedPond = koiPondService.updatePond(id, pondDTO);
        return ResponseEntity.ok(updatedPond);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePond(@PathVariable Long id) {
        koiPondService.deletePond(id);
        return ResponseEntity.noContent().build();
    }
}
