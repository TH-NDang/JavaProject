package org.group.koipondbackend.service.impl;

import org.group.koipondbackend.dto.RatingDTO;
import org.group.koipondbackend.entity.Rating;
import org.group.koipondbackend.mapper.RatingMapper;
import org.group.koipondbackend.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;

    @Autowired
    public RatingService(RatingRepository ratingRepository, RatingMapper ratingMapper) {
        this.ratingRepository = ratingRepository;
        this.ratingMapper = ratingMapper;
    }

    public List<RatingDTO> getAllRatings() {
        return ratingRepository.findAll().stream()
                .map(ratingMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<RatingDTO> getRatingById(Long id) {
        return ratingRepository.findById(id)
                .map(ratingMapper::toDto);
    }

    public RatingDTO createRating(RatingDTO ratingDTO) {
        Rating rating = ratingMapper.toEntity(ratingDTO);
        Rating savedRating = ratingRepository.save(rating);
        return ratingMapper.toDto(savedRating);
    }

    public Optional<RatingDTO> updateRating(Long id, RatingDTO ratingDTO) {
        return ratingRepository.findById(id)
                .map(rating -> {
                    rating.setRating(ratingDTO.getRating());
                    rating.setComment(ratingDTO.getComment());
                    Rating updatedRating = ratingRepository.save(rating);
                    return ratingMapper.toDto(updatedRating);
                });
    }

    public void deleteRating(Long id) {
        ratingRepository.deleteById(id);
    }

    public List<RatingDTO> getRatingsByCustomerId(Long customerId) {
        return ratingRepository.findByCustomerId(customerId).stream()
                .map(ratingMapper::toDto)
                .collect(Collectors.toList());
    }
}
