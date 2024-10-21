package org.group.koipondbackend.controller;

import org.group.koipondbackend.dto.RatingDTO;
import org.group.koipondbackend.service.impl.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping
    public List<RatingDTO> getAllRatings() {
        return ratingService.getAllRatings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingDTO> getRatingById(@PathVariable Long id) {
        Optional<RatingDTO> rating = ratingService.getRatingById(id);
        return rating.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public RatingDTO createRating(@RequestBody RatingDTO ratingDTO) {
        return ratingService.createRating(ratingDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RatingDTO> updateRating(@PathVariable Long id, @RequestBody RatingDTO ratingDTO) {
        Optional<RatingDTO> updatedRating = ratingService.updateRating(id, ratingDTO);
        return updatedRating.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id) {
        ratingService.deleteRating(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/customers/{id}")
    public List<RatingDTO> getRatingsByCustomerId(@PathVariable Long id) {
        return ratingService.getRatingsByCustomerId(id);
    }
}
