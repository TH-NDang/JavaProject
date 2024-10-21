package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.KoiPondDTO;
import org.group.koipondbackend.entity.KoiPond;
import org.springframework.stereotype.Component;

@Component
public class KoiPondMapper {
    public KoiPondDTO toDto(KoiPond pond) {
        if (pond == null) {
            return null;
        }
        KoiPondDTO dto = new KoiPondDTO();
        dto.setId(pond.getId());
        dto.setName(pond.getName());
        dto.setSizeInLiters(pond.getSizeInLiters());
        dto.setDepthInMeters(pond.getDepthInMeters());
        dto.setShape(pond.getShape());
        dto.setFiltrationSystem(pond.getFiltrationSystem());
        dto.setWaterQuality(pond.getWaterQuality());
        dto.setDescription(pond.getDescription());
        dto.setImagePaths(pond.getImagePaths());
        return dto;
    }

    public KoiPond toEntity(KoiPondDTO dto) {
        if (dto == null) {
            return null;
        }
        KoiPond pond = new KoiPond();
        pond.setId(dto.getId());
        pond.setName(dto.getName());
        pond.setSizeInLiters(dto.getSizeInLiters());
        pond.setDepthInMeters(dto.getDepthInMeters());
        pond.setShape(dto.getShape());
        pond.setFiltrationSystem(dto.getFiltrationSystem());
        pond.setWaterQuality(dto.getWaterQuality());
        pond.setDescription(dto.getDescription());
        pond.setImagePaths(dto.getImagePaths());
        return pond;
    }
}
