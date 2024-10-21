package org.group.koipondbackend.controller;

import org.group.koipondbackend.dto.CustomerDTO;
import org.group.koipondbackend.dto.OrderDTO;
import org.group.koipondbackend.dto.ProjectDTO;
import org.group.koipondbackend.service.impl.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    @Autowired
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/stats")
    public Map<String, Object> getDashboardStats() {
        return dashboardService.getDashboardStats();
    }

    @GetMapping("/recent-projects")
    public List<ProjectDTO> getRecentProjects() {
        return dashboardService.getRecentProjects();
    }

    @GetMapping("/recent-orders")
    public List<OrderDTO> getRecentOrders() {
        return dashboardService.getRecentOrders();
    }

    @GetMapping("/top-customers")
    public List<CustomerDTO> getTopCustomers() {
        return dashboardService.getTopCustomers();
    }
}
