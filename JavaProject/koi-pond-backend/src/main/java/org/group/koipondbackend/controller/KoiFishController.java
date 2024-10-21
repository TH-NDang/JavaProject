package org.group.koipondbackend.controller;

import org.group.koipondbackend.dto.KoiFishDTO;
import org.group.koipondbackend.entity.KoiFish;
import org.group.koipondbackend.mapper.KoiFishMapper;
import org.group.koipondbackend.service.KoiFishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/koi-fish")
public class KoiFishController {

    @Autowired
    private KoiFishService koiFishService;

    @Autowired
    private KoiFishMapper koiFishMapper;

    @GetMapping
    public List<KoiFishDTO> getAllKoiFish() {
        List<KoiFish> koiFishList = koiFishService.getAllKoiFish();
        return koiFishMapper.toDtoList(koiFishList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KoiFishDTO> getKoiFishById(@PathVariable Long id) {
        KoiFish koiFish = koiFishService.getKoiFishById(id);
        if (koiFish == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(koiFishMapper.toDto(koiFish));
    }

    @PostMapping
    public KoiFishDTO createKoiFish(@RequestBody KoiFishDTO koiFishDTO) {
        KoiFish koiFish = koiFishMapper.toEntity(koiFishDTO);
        koiFish = koiFishService.createKoiFish(koiFish);
        return koiFishMapper.toDto(koiFish);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KoiFishDTO> updateKoiFish(@PathVariable Long id, @RequestBody KoiFishDTO koiFishDTO) {
        KoiFish koiFish = koiFishService.updateKoiFish(id, koiFishMapper.toEntity(koiFishDTO));
        if (koiFish == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(koiFishMapper.toDto(koiFish));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKoiFish(@PathVariable Long id) {
        if (koiFishService.deleteKoiFish(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/ponds/{pondId}")
    public List<KoiFishDTO> getKoiFishInPond(@PathVariable Long pondId) {
        List<KoiFish> koiFishList = koiFishService.getKoiFishInPond(pondId);
        return koiFishMapper.toDtoList(koiFishList);
    }
}
