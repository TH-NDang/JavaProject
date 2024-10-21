package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.ConstructionStaffDTO;
import org.group.koipondbackend.entity.ConstructionStaff;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConstructionStaffMapper extends GenericMapper<ConstructionStaff, ConstructionStaffDTO> {

    ConstructionStaffDTO toDto(ConstructionStaff entity);

    ConstructionStaff toEntity(ConstructionStaffDTO dto);
}
