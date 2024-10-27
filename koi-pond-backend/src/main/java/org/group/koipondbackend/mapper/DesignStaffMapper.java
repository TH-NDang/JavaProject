package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.DesignStaffDTO;
import org.group.koipondbackend.entity.DesignStaff;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DesignStaffMapper extends GenericMapper<DesignStaff, DesignStaffDTO> {

    DesignStaffDTO toDto(DesignStaff entity);

    DesignStaff toEntity(DesignStaffDTO dto);
}
