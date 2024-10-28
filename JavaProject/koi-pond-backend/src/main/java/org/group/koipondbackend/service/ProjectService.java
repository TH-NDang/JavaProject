package org.group.koipondbackend.service;

import org.group.koipondbackend.dto.ProjectDTO;
import org.group.koipondbackend.dto.OrderDTO;
import java.util.List;

public interface ProjectService {
    List<ProjectDTO> findAll();
    ProjectDTO findById(Long id);
    ProjectDTO create(ProjectDTO projectDto);
    ProjectDTO update(Long id, ProjectDTO projectDto);
    void delete(Long id);
    List<OrderDTO> findOrdersByProjectId(Long projectId);
}
