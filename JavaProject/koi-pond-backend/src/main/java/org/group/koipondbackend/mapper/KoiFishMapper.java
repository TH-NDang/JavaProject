package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.KoiFishDTO;
import org.group.koipondbackend.entity.KoiFish;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class KoiFishMapper {

    public KoiFishDTO toDto(KoiFish entity) {
        if (entity == null) {
            return null;
        }
        KoiFishDTO dto = new KoiFishDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setColor(entity.getColor());
        dto.setSize(entity.getSize());
        dto.setPondId(entity.getPond().getId());
        return dto;
    }

    public KoiFish toEntity(KoiFishDTO dto) {
        if (dto == null) {
            return null;
        }
        KoiFish entity = new KoiFish();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setColor(dto.getColor());
        entity.setSize(dto.getSize());
        return entity;
    }

    public List<KoiFishDTO> toDtoList(List<KoiFish> koiFishList) {
        return koiFishList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
