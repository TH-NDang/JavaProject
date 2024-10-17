package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.ProjectDTO;
import org.group.koipondbackend.entity.Project;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectMapper {

    // Chuyển đổi từ Project entity sang ProjectDTO
    public ProjectDTO toDto(Project entity) {
        if (entity == null) {
            return null;
        }

        ProjectDTO dto = new ProjectDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        // Thêm các thuộc tính khác nếu cần

        return dto;
    }

    // Chuyển đổi từ ProjectDTO sang Project entity
    public Project toEntity(ProjectDTO dto) {
        if (dto == null) {
            return null;
        }

        Project entity = new Project();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        // Thêm các thuộc tính khác nếu cần

        return entity;
    }

    // Chuyển đổi danh sách Project entity sang danh sách ProjectDTO
    public List<ProjectDTO> toDtoList(List<Project> projects) {
        return projects.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}