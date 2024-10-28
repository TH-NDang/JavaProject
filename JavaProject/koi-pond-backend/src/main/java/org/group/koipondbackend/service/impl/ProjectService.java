package org.group.koipondbackend.service.impl;

import java.util.List;
import java.util.Optional;

import org.group.koipondbackend.dto.ProjectDTO;
import org.group.koipondbackend.entity.Project;
import org.group.koipondbackend.exception.ResourceNotFoundException;
import org.group.koipondbackend.mapper.ProjectMapper;
import org.group.koipondbackend.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectDTO create(ProjectDTO projectDto) {
        Project project = projectMapper.toEntity(projectDto);
        return projectMapper.toDto(projectRepository.save(project));
    }

    public Optional<ProjectDTO> findById(Long id) {
        return projectRepository.findById(id).map(projectMapper::toDto);
    }

    public List<ProjectDTO> findAll() {
        return projectMapper.toDto(projectRepository.findAll());
    }

    public ProjectDTO update(ProjectDTO projectDto) {
        if (!projectRepository.existsById(projectDto.getId())) {
            throw new ResourceNotFoundException("Project not found with id " + projectDto.getId());
        }
        Project project = projectMapper.toEntity(projectDto);
        return projectMapper.toDto(projectRepository.save(project));
    }

    public void delete(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Project not found with id " + id);
        }
        projectRepository.deleteById(id);
    }
}
