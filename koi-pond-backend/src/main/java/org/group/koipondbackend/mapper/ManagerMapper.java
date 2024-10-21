package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.ManagerDTO;
import org.group.koipondbackend.entity.Manager;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ManagerMapper extends GenericMapper<Manager, ManagerDTO> {

    ManagerDTO toDto(Manager entity);

    Manager toEntity(ManagerDTO dto);
}
