package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.ProjectDTO;
import org.group.koipondbackend.entity.Project;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectMapper {

    public ProjectDTO toDto(Project entity) {
        if (entity == null) {
            return null;
        }
        ProjectDTO dto = new ProjectDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public Project toEntity(ProjectDTO dto) {
        if (dto == null) {
            return null;
        }
        Project entity = new Project();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    public List<ProjectDTO> toDtoList(List<Project> projects) {
        return projects.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
