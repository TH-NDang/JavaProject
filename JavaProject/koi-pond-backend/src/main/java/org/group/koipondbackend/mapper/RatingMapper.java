package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.RatingDTO;
import org.group.koipondbackend.entity.Rating;
import org.springframework.stereotype.Component;

@Component
public class RatingMapper {
    public RatingDTO toDto(Rating entity) {
        if (entity == null) return null;

        RatingDTO dto = new RatingDTO();
        dto.setId(entity.getId());
        dto.setRating(entity.getRating());
        dto.setComment(entity.getComment());
        dto.setCustomerId(entity.getCustomer().getId());

        return dto;
    }

    public Rating toEntity(RatingDTO dto) {
        if (dto == null) return null;

        Rating entity = new Rating();
        entity.setId(dto.getId());
        entity.setRating(dto.getRating());
        entity.setComment(dto.getComment());

        return entity;
    }
}
