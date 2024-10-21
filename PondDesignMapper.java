package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.PondDesignDTO;
import org.group.koipondbackend.entity.PondDesign;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PondDesignMapper {

    public PondDesignDTO toDto(PondDesign entity) {
        if (entity == null) {
            return null;
        }

        PondDesignDTO dto = new PondDesignDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setCostEstimate(entity.getCostEstimate());

        return dto;
    }

    public PondDesign toEntity(PondDesignDTO dto) {
        if (dto == null) {
            return null;
        }

        PondDesign entity = new PondDesign();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setCostEstimate(dto.getCostEstimate());

        return entity;
    }

    public List<PondDesignDTO> toDtoList(List<PondDesign> pondDesigns) {
        return pondDesigns.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
