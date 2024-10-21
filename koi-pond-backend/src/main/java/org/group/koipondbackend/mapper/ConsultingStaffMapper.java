package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.ConsultingStaffDTO;
import org.group.koipondbackend.entity.ConsultingStaff;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsultingStaffMapper extends GenericMapper<ConsultingStaff, ConsultingStaffDTO> {
    ConsultingStaffDTO toDto(ConsultingStaff entity);

    ConsultingStaff toEntity(ConsultingStaffDTO dto);
}
