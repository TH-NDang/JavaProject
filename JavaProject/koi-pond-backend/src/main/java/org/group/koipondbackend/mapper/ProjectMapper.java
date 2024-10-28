package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.ProjectDTO;
import org.group.koipondbackend.entity.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper extends BaseMapper<Project, ProjectDTO> {
    @Override
    public ProjectDTO toDto(Project entity) {
        if (entity == null) {
            return null;
        }

        return new ProjectDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getStartDate(),
                entity.getEndDate()
        );
    }

    @Override
    public Project toEntity(ProjectDTO dto) {
        if (dto == null) {
            return null;
        }

        return new Project(
                dto.getId(),
                dto.getName(),
                dto.getDescription(),
                dto.getStatus(),
                dto.getStartDate(),
                dto.getEndDate()
        );
    }
}
