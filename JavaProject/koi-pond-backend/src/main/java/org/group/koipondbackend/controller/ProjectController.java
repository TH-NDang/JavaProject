package org.group.koipondbackend.controller;

import java.util.List;

import org.group.koipondbackend.dto.ProjectDTO;
import org.group.koipondbackend.service.impl.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.group.koipondbackend.exception.ResourceNotFoundException;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDto) {
        ProjectDTO createdProject = projectService.create(projectDto);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id) {
        ProjectDTO project = projectService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + id));
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        List<ProjectDTO> projects = projectService.findAll();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable Long id, @RequestBody ProjectDTO projectDto) {
        projectDto.setId(id);
        ProjectDTO updatedProject = projectService.update(projectDto);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) {
        projectService.delete(id);
        return ResponseEntity.ok("Project deleted successfully");
    }

    // Nếu muốn thêm endpoint lấy danh sách đơn hàng cho một dự án
    @GetMapping("/{id}/orders")
    public ResponseEntity<List<OrderDTO>> getOrdersByProjectId(@PathVariable Long id) {
        List<OrderDTO> orders = projectService.findOrdersByProjectId(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
