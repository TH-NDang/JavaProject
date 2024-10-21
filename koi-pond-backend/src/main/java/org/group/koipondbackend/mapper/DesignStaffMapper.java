package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.DesignStaffDTO;
import org.group.koipondbackend.entity.DesignStaff;
import org.springframework.stereotype.Component;

@Component
public interface DesignStaffMapper extends GenericMapper<DesignStaff, DesignStaffDTO> {

    DesignStaffDTO toDto(DesignStaff entity);

    DesignStaff toEntity(DesignStaffDTO dto);
}
