package org.group.koipondbackend.mapper;

import org.group.koipondbackend.dto.ProjectDTO;
import org.group.koipondbackend.entity.Project;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectDTO toDto(Project project);

    Project toEntity(ProjectDTO projectDTO);

    default List<ProjectDTO> toDtoList(List<Project> projects) {
        return projects.stream().map(this::toDto).collect(Collectors.toList());
    }
}
