package org.group.koipondbackend.controller;

import org.group.koipondbackend.dto.ProjectDTO;
import org.group.koipondbackend.dto.OrderDTO; // Nhập OrderDTO
import org.group.koipondbackend.service.impl.OrderService; // Nhập OrderService
import org.group.koipondbackend.service.impl.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final OrderService orderService; // Khai báo OrderService

    @Autowired
    public ProjectController(ProjectService projectService, OrderService orderService) {
        this.projectService = projectService;
        this.orderService = orderService; // Inject OrderService
    }

    @GetMapping
    public List<ProjectDTO> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {
        ProjectDTO createdProject = projectService.createProject(projectDTO);
        return ResponseEntity.created(URI.create("/api/projects/" + createdProject.getId()))
                .body(createdProject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
        return projectService.updateProject(id, projectDTO)
                .map(updatedProject -> ResponseEntity.ok(updatedProject))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        if (projectService.deleteProject(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/orders")
    public List<OrderDTO> getOrdersByProjectId(@PathVariable Long id) {
        return orderService.getOrdersByProjectId(id); // Sử dụng orderService
    }
}